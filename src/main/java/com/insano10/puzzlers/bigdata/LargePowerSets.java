package com.insano10.puzzlers.bigdata;

import com.insano10.puzzlers.sets.Powerset;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class LargePowerSets
{
    private static class SetWritable implements Writable
    {
        private final IntWritable size = new IntWritable(0);
        private final Set<IntWritable> set = new HashSet<>();

        public void addAll(Set<IntWritable> subSet)
        {
            set.addAll(subSet);
            size.set(set.size());
        }

        public Set<IntWritable> asSet()
        {
            return set;
        }

        @Override
        public void write(DataOutput dataOutput) throws IOException
        {
            size.write(dataOutput);
            for (IntWritable element : set)
            {
                element.write(dataOutput);
            }
        }

        @Override
        public void readFields(DataInput dataInput) throws IOException
        {
            size.readFields(dataInput);
            for(int i=0 ; i<size.get() ; i++)
            {
                IntWritable intWritable = new IntWritable();
                intWritable.readFields(dataInput);
                set.add(intWritable);
            }
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SetWritable that = (SetWritable) o;

            if (!size.equals(that.size)) return false;
            return set.equals(that.set);

        }

        @Override
        public int hashCode()
        {
            int result = size.hashCode();
            result = 31 * result + set.hashCode();
            return result;
        }

        @Override
        public String toString()
        {
            return set.toString();
        }
    }

    private static class PowerSetWritable implements Writable
    {
        private final IntWritable size = new IntWritable(0);
        private final Set<SetWritable> set = new HashSet<>();

        public void add(SetWritable element)
        {
            set.add(element);
            size.set(set.size());
        }

        public Set<SetWritable> asSetOfSetWritables()
        {
            Set<SetWritable> setOfSets = new HashSet<>();
            for (SetWritable setWritable : set)
            {
                setOfSets.add(setWritable);
            }
            return setOfSets;
        }

        @Override
        public void write(DataOutput dataOutput) throws IOException
        {
            size.write(dataOutput);
            for (SetWritable setWritable : set)
            {
                setWritable.write(dataOutput);
            }
        }

        @Override
        public void readFields(DataInput dataInput) throws IOException
        {
            size.readFields(dataInput);
            for(int i=0 ; i<size.get() ; i++)
            {
                SetWritable setWritable = new SetWritable();
                setWritable.readFields(dataInput);
                this.set.add(setWritable);
            }
        }

        public boolean isEmpty()
        {
            return set.isEmpty();
        }

        @Override
        public String toString()
        {
            return "total sets: [" + set.size() + "] -> " + set.toString();
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PowerSetWritable that = (PowerSetWritable) o;

            if (!size.equals(that.size)) return false;
            return set.equals(that.set);

        }

        @Override
        public int hashCode()
        {
            int result = size.hashCode();
            result = 31 * result + set.hashCode();
            return result;
        }
    }

    public static class PowerSetMapper extends Mapper<Object, Text, Text, PowerSetWritable>
    {

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException
        {
            //value is our smaller set in a comma separated line (e.g.{ 0,1,2,3,4,5,6,7,8,9,10}
            StringTokenizer tokenizer = new StringTokenizer(value.toString(), ",");

            Set<IntWritable> set = new HashSet<>();
            while(tokenizer.hasMoreTokens())
            {
                set.add(new IntWritable(Integer.parseInt(tokenizer.nextToken())));
            }

            Set<Set<IntWritable>> powerSet = Powerset.usingBinaryString(set);

            PowerSetWritable powerSetWritable = new PowerSetWritable();
            for (Set<IntWritable> subset : powerSet)
            {
                SetWritable setWritable = new SetWritable();
                setWritable.addAll(subset);
                powerSetWritable.add(setWritable);
            }

            context.write(new Text("theOnlyKey"), powerSetWritable);
        }

    }

    public static class PowerSetReducer extends Reducer<Text, PowerSetWritable, Text, PowerSetWritable>
    {
        public void reduce(Text key, Iterable<PowerSetWritable> values, Context context) throws IOException, InterruptedException
        {
            PowerSetWritable resultPowerSet = new PowerSetWritable();

            for (PowerSetWritable subPowerSet : values)
            {
                if(resultPowerSet.isEmpty())
                {
                    for (SetWritable subSet : subPowerSet.asSetOfSetWritables())
                    {
                        resultPowerSet.add(subSet);
                    }
                }
                else
                {
                    for(SetWritable existingSet : resultPowerSet.asSetOfSetWritables())
                    {
                        for(SetWritable subSet : subPowerSet.asSetOfSetWritables())
                        {
                            SetWritable copyOfExistingSet = new SetWritable();
                            copyOfExistingSet.addAll(existingSet.asSet());
                            copyOfExistingSet.addAll(subSet.asSet());

                            resultPowerSet.add(copyOfExistingSet);
                        }
                    }
                }
            }

            context.write(new Text("overall powerset"), resultPowerSet);
        }

    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "power sets");
        job.setJarByClass(LargePowerSets.class);
        job.setMapperClass(PowerSetMapper.class);
        job.setCombinerClass(PowerSetReducer.class);
        job.setReducerClass(PowerSetReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(PowerSetWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(PowerSetWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
