package com.insano10.puzzlers;

public class SpiralArrayTraversalNN
{
    /*
    [o][o][o][o][o][o][o][o][o][o] <- start here
    [o][-][-][-][-][-][-][-][-][o]
    [o][-][o][o][o][o][o][o][-][o]
    [o][-][o][-][-][-][-][o][-][o]
    [o][-][o][-][o][o][-][o][-][o]
    [o][-][o][-][o][o][-][o][-][o]
    [o][-][o][-][-][-][-][o][-][o]
    [o][-][o][o][o][o][o][o][-][o]
    [o][-][-][-][-][-][-][-][-][o]
    [o][o][o][o][o][o][o][o][o][o]
     */

    public static void main(String[] args)
    {
        int sideLength = 10;
        int[][] array = new int[sideLength][sideLength];

        array = fill(array, sideLength);

        printArray(sideLength, array);
    }

    private static int[][] fill(int[][] array, int sideLength)
    {
        int stepsToWalk = sideLength - 1;
        int stepCount = 1;
        int circuitNum = 0;

        while (stepsToWalk > 0)
        {
            //walk 4 sides of each circuit
            for (int i = 0; i < 4; i++)
            {
                walkRow(array, sideLength, stepsToWalk, circuitNum, stepCount);
                stepCount += stepsToWalk;
                array = ArrayRotation.rotate90Degrees(array);
            }
            //move inwards to the next circuit
            circuitNum++;

            //walk 2 fewer steps this circuit as the side length has decreased by 2
            stepsToWalk -= 2;
        }

        return array;
    }

    private static void walkRow(int[][] array, int sideLength, int stepsToWalk, int circuitNum, int stepCount)
    {
        int startingIndex = sideLength - 1 - circuitNum;

        for (int i = 0; i < stepsToWalk; i++)
        {
            array[circuitNum][startingIndex-i] = stepCount++;
        }
    }


    private static void printArray(int sideLength, int[][] array)
    {
        for (int i = 0; i < sideLength; i++)
        {

            for (int j = 0; j < sideLength; j++)
            {
                System.out.print("[" + String.format("%03d", array[i][j]) + "]");
            }
            System.out.println("\n");
        }
        System.out.println("\n\n");
    }

}
