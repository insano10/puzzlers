package com.insano10.puzzlers.puzzles.codility.timecomplexity;

public class TapeEquilibrium
{
    public int solution(int[] input)
    {
        int total = 0;

        for (int i = 0; i < input.length; i++)
        {
            total += input[i];
        }

        int leftSide = input[0];
        int rightSide = total - leftSide;
        int minDiff = Math.abs(leftSide - rightSide);

        for (int i = 1; i < input.length - 1; i++)
        {
            leftSide += input[i];
            rightSide -= input[i];
            minDiff = Math.min(minDiff, Math.abs(leftSide - rightSide));
        }

        return minDiff;
    }
}
