package com.insano10.puzzlers.puzzles.codility.prefixsums;

public class CountDiv
{
    public int solution(int A, int B, int K)
    {
        int countWithinRange = B / K - A / K;

        //add 1 if A is also divisible by K (the lower bound is inclusive)
        if (countWithinRange < Integer.MAX_VALUE && A % K == 0)
        {
            countWithinRange++;
        }
        return countWithinRange;
    }
}
