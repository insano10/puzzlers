package com.insano10.puzzlers.puzzles.codility.sorting;

import java.util.Arrays;

public class NumberOfDiscIntersections
{
    public int solution(int[] A)
    {
        long[][] discBounds = new long[A.length][2];
        long[] upperBounds = new long[A.length];

        for (int i = 0; i < A.length; i++)
        {
            discBounds[i][0] = (long)i - A[i];
            discBounds[i][1] = (long)i + A[i];
            upperBounds[i] = (long)i + A[i];
        }

        //sort discBounds in order of lower bound
        Arrays.sort(discBounds, (o1, o2) -> {
            if (o1[0] < o2[0])
            {
                return -1;
            }
            else if (o1[0] == o2[0])
            {
                return 0;
            }
            else
            {
                return 1;
            }
        });

        //sort the upper bounds
        Arrays.sort(upperBounds);

        int openDiscs = 0;
        int intersections = 0;
        int nextDiscToClose = 0;

        for (int i = 0; i < discBounds.length; i++)
        {
            //close any now finished discs
            while (nextDiscToClose < upperBounds.length && upperBounds[nextDiscToClose] < discBounds[i][0])
            {
                openDiscs--;
                nextDiscToClose++;
            }

            //open the new disc
            openDiscs++;

            //add intersections
            intersections += (openDiscs - 1);

            if (intersections > 10_000_000)
            {
                return -1;
            }
        }
        return intersections;
    }
}
