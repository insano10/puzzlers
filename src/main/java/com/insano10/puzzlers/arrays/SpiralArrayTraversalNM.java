package com.insano10.puzzlers.arrays;

public class SpiralArrayTraversalNM
{
    /*
    [o][o][o][o][o][o][o][o][o][o][o][o] <- start here
    [o][-][-][-][-][-][-][-][-][-][-][o]
    [o][-][o][o][o][o][o][o][o][o][-][o]
    [o][-][o][-][-][-][-][-][-][o][-][o]
    [o][-][o][-][o][o][o][o][-][o][-][o]
    [o][-][o][-][o][o][o][o][-][o][-][o]
    [o][-][o][-][-][-][-][-][-][o][-][o]
    [o][-][o][o][o][o][o][o][o][o][-][o]
    [o][-][-][-][-][-][-][-][-][-][-][o]
    [o][o][o][o][o][o][o][o][o][o][o][o]
     */

    public static void main(String[] args)
    {
        int width = 12;
        int height = 10;
        int[][] array = new int[height][width];

        array = fill(array);

        printArray(array);
    }

    private static int[][] fill(int[][] array)
    {
        int width = array[0].length;
        int height = array.length;

        int horizStepsToWalk = width - 1;
        int vertStepsToWalk = height - 1;
        int stepCount = 1;
        int circuitNum = 0;

        while (horizStepsToWalk > 0 || vertStepsToWalk > 0)
        {
            //walk 4 sides of each circuit
            for (int i = 0; i < 4; i++)
            {
                if(i % 2 == 0)
                {
                    //horiz
                    walkRow(array, width, horizStepsToWalk, circuitNum, stepCount);
                    stepCount += horizStepsToWalk;
                }
                else
                {
                    //vert
                    walkRow(array, height, vertStepsToWalk, circuitNum, stepCount);
                    stepCount += vertStepsToWalk;
                }
                array = ArrayRotation.rotate90Degrees(array);

                printArray(array);
            }
            //move inwards to the next circuit
            circuitNum++;

            //walk 2 fewer steps this circuit as the side length has decreased by 2
            horizStepsToWalk -= 2;
            vertStepsToWalk -= 2;
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


    private static void printArray(int[][] array)
    {
        int width = array.length;
        int height = array[0].length;
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                System.out.print("[" + String.format("%03d", array[i][j]) + "]");
            }
            System.out.println("\n");
        }
        System.out.println("\n\n");
    }

}
