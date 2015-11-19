package com.insano10.puzzlers.puzzles.codility.prefixsums;

public class PassingCars
{
    public int solution(int[] input)
    {
        int[] prefixSumOfCarsEast = new int[input.length];

        prefixSumOfCarsEast[0] = (input[0] == 0) ? 1 : 0;

        for (int i = 1; i < input.length; i++)
        {
            prefixSumOfCarsEast[i] = prefixSumOfCarsEast[i - 1] + (input[i] == 0 ? 1 : 0);
        }

        int numPairs = 0;
        for (int i = 0; i < input.length; i++)
        {
            if(input[i] == 1)
            {
                numPairs += prefixSumOfCarsEast[i];
            }

            if(numPairs > 1_000_000_000)
            {
                return -1;
            }
        }

        return numPairs;
    }
}
