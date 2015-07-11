package com.insano10.puzzlers.puzzles.woutercoekaerts;

public class PuzzlerRunner
{
    public static void run(Runnable puzzle)
    {
        //don't allow reflection - too easy!
        System.setSecurityManager(new SecurityManager());

        puzzle.run();
    }

}
