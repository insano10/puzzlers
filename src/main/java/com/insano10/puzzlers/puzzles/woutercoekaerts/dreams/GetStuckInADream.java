package com.insano10.puzzlers.puzzles.woutercoekaerts.dreams;

import com.insano10.puzzlers.puzzles.woutercoekaerts.PuzzlerRunner;

public class GetStuckInADream implements Runnable
{
    /*
        Make a Sleeper dream a Dream and never wake up

        You are not allowed to touch Sleeper or GetStuckInADream

     */

    @Override
    public void run()
    {
        if (new Sleeper().enter(new Dream()) != 0)
        {
            // The goal is to reach this line
            System.out.println("Am I still dreaming?");
        }
    }

    public static void main(String[] args)
    {
        PuzzlerRunner.run(new GetStuckInADream());
    }
}
