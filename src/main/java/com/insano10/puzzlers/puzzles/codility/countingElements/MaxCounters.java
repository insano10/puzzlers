package com.insano10.puzzlers.puzzles.codility.countingElements;

public class MaxCounters
{
    /*
    this solution only scores 77% because it is O(n*m)
    however the solution that does it in O(n) is not very readable so I prefer this one
     */
    public int[] solution(int N, int[] input)
    {
        //already initialised to zero
        int[] counters = new int[N];

        int maxCounter = 0;

        for (int i = 0; i < input.length; i++)
        {
            if (input[i] <= N)
            {
                counters[input[i] - 1] += 1;
                maxCounter = Math.max(maxCounter, counters[input[i] - 1]);
            }
            else
            {
                for (int j = 0; j < N; j++)
                {
                    counters[j] = maxCounter;
                }
            }
        }

        return counters;
    }

    public int[] fasterButNastier(int N, int[] input)
    {
        //already initialised to zero
        int[] counters = new int[N];

        int baseValue = 0;
        int maxCounter = 0;

        for (int i = 0; i < input.length; i++)
        {
            if (input[i] <= N)
            {
                if (counters[input[i] - 1] < baseValue)
                {
                    counters[input[i] - 1] = baseValue;
                }
                counters[input[i] - 1] += 1;
                maxCounter = Math.max(maxCounter, counters[input[i] - 1]);
            }
            else
            {
                baseValue = maxCounter;
            }
        }

        for (int i = 0; i < N; i++)
        {
            counters[i] = Math.max(baseValue, counters[i]);
        }

        return counters;
    }
}
