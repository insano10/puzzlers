package com.insano10.puzzlers.puzzles.codility.sorting;

import java.util.Arrays;

public class Triangle
{
    /*

    These 3 conditions need to hold true for a triangle:

    1) A[P] + A[Q] > A[R],
    2) A[Q] + A[R] > A[P],
    3) A[R] + A[P] > A[Q].

    If we sort the array and look for consecutive triplets from lowest to highest then 2) and 3) always hold true as R is always the biggest number of the 3

    We only need to check consecutive triplets as this gives the greatest possible value of A[P] + A[Q]
    So there may also be non-consecutive triplets present but we only have to return whether any are present at all.

    So the only condition we need to check for a valid triangle is 1)

     */
    public int solution(int[] A)
    {
        Arrays.sort(A);

        for (int i = 0; i < A.length - 2; i++)
        {
            long p = A[i];
            long q = A[i + 1];
            long r = A[i + 2];

            if (p + q > r)
            {
                return 1;
            }
        }
        return 0;
    }
}
