package com.insano10.puzzlers.puzzles.woutercoekaerts.dreams;

public class Dream
{
    public void dream(Sleeper s)
    {
        System.out.println("starting outer dream");

        //start a new dream that cannot actually start until the current lock is released
        new Thread(() -> s.enter(new NeverEndingDream())).start();

        releaseTheSleeperLock(s);

        System.out.println("finished outer dream");
    }

    private static class NeverEndingDream extends Dream
    {
        @Override
        public void dream(Sleeper s)
        {
            System.out.println("starting never ending dream");
            try
            {
                s.wait();
            }
            catch (InterruptedException e)
            {
                System.out.println("interrupted");
            }

            System.out.println("WTF?");
        }
    }

    private static void releaseTheSleeperLock(Sleeper s)
    {
        try
        {
            s.wait(100);
        }
        catch (InterruptedException e)
        {
            System.out.println("interrupted");
        }
    }
}