package com.insano10.puzzlers.puzzles.woutercoekaerts.dreams;

public class Sleeper
{
    private int level;

    public synchronized int enter(Dream dream)
    {
        level++;
        try
        {
            dream.dream(this);
        }
        finally
        {
            level--;
        }
        return level;
    }
}