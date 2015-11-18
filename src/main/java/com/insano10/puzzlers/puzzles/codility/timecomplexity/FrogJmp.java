package com.insano10.puzzlers.puzzles.codility.timecomplexity;

public class FrogJmp
{
    public static int solution(int X, int Y, int D)
    {
        double distanceToCover = (double) (Y - X);
        double numJumps = Math.ceil(distanceToCover / D);
        return (int) numJumps;
    }
}
