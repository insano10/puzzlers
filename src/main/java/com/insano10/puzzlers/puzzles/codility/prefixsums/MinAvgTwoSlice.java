package com.insano10.puzzlers.puzzles.codility.prefixsums;

public class MinAvgTwoSlice
{
    /*

    It is sufficient to check only the slices of length 2 and 3.
    This is because any slice of length 2+ can be broken down into smaller slices of either size 2 or 3

    I do not believe it is possible to have a slice average smaller than that of its constituent parts.
    In a slice the elements are either:

    a) all the same
    b) different (i.e. some are higher/lower than others)

    If the elements are all the same then the smaller 2/3 slice will have the same average as the parent slice
    If the elements are different then you can just make a smaller slice including the lowest element to achieve a lower average


    e.g.
            [2, 4, 2, -2]

                    2slice  3slice  4slice
            [0] =     3     2.666   1.5
            [1] =     3     1.333    -
            [2] =     0       -      -
            [3] =     -       -      -

    It may appear that you could simply make the last element small enough to negate the other elements, thus ending up with a 4slice average of 0
    But remember that negative averages are lower than zero, so the 2slice including that element will still produce a smaller average

            [2, 4, 2, -8]

                    2slice  3slice  4slice
            [0] =     3     2.666    0
            [1] =     3     0.666    -
            [2] =    -3       -      -
            [3] =     -       -      -

     */
    public int solution(int[] input)
    {
        int[] prefixSum = new int[input.length];
        prefixSum[0] = input[0];

        for (int i = 1; i < input.length; i++)
        {
            prefixSum[i] = prefixSum[i - 1] + input[i];
        }

        int minAverageSliceStartIndex = 0;
        double globalMinAvg = Double.MAX_VALUE;

        if (prefixSum.length > 2)
        {
            for (int i = 0; i < prefixSum.length - 2; i++)
            {
                int offsetVal = (i == 0) ? 0 : prefixSum[i - 1];

                //calculate the avg of the 2slice and 3slice starting at this point
                double minAvgAtThisIdx = Math.min(
                        (prefixSum[i + 1] - offsetVal) / 2d,
                        (prefixSum[i + 2] - offsetVal) / 3d);

                if (minAvgAtThisIdx < globalMinAvg)
                {
                    globalMinAvg = minAvgAtThisIdx;
                    minAverageSliceStartIndex = i;
                }
            }
        }

        //calculate the final 2slice
        int lastSlicePrefix = prefixSum.length < 3 ? 0 : prefixSum[prefixSum.length - 3];
        if ((prefixSum[prefixSum.length - 1] - lastSlicePrefix) / 2d < globalMinAvg)
        {
            minAverageSliceStartIndex = prefixSum.length - 2;
        }

        return minAverageSliceStartIndex;
    }
}
