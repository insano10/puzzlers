package com.insano10.puzzlers.puzzles.codility.timecomplexity;

public class PermMissingElement
{
    public int solution(int[] input)
    {
        int completeValue = input.length + input.length+1;
        int actualValue = 0;

        for(int i=0 ; i<input.length ; i++)
        {
            actualValue += input[i];
            completeValue += i;
        }
        return completeValue - actualValue;
    }
}
