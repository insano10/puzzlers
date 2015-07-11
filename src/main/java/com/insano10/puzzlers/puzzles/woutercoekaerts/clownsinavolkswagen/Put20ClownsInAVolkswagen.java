package com.insano10.puzzlers.puzzles.woutercoekaerts.clownsinavolkswagen;

import com.insano10.puzzlers.puzzles.woutercoekaerts.PuzzlerRunner;

public class Put20ClownsInAVolkswagen implements Runnable
{
    /*
        Call done() on Volkswagen and expect there to be 20 Clowns in the car

        You are not allowed to touch Clown or Volkswagen

     */

    @Override
    public void run()
    {
        Volkswagen vw = new Volkswagen();

        try
        {
            vw.add(new StealthClown(vw, 19));
        }
        finally
        {
            vw.done();
        }
    }

    private static class StealthClown extends Clown
    {

        private Volkswagen volkswagen;
        private int extras;
        public StealthClown(Volkswagen volkswagen, int extras)
        {
            this.volkswagen = volkswagen;
            this.extras = extras;
        }

        @Override
        public int hashCode()
        {
            if(extras > 0)
            {
                volkswagen.add(new StealthClown(volkswagen, --extras));
            }

            return super.hashCode();
        }

    }

    public static void main(String args[])
    {
        PuzzlerRunner.run(new Put20ClownsInAVolkswagen());
    }
}
