package com.insano10.puzzlers.arrays;

public class ArrayRotation
{
    /*

    1D array has height. height = array.length

    [0]
    [1]
    [2]
    [3]
    [4]
    [5]

    2D array has width. width = array[0].length

    [0] -> [0][1][2]
    [1] -> [0][1][2]
    [2] -> [0][1][2]
    [3] -> [0][1][2]
    [4] -> [0][1][2]
    [5] -> [0][1][2]

     */

    public static int[][] rotate90Degrees(int[][] array)
    {
        int width = array[0].length;
        int height = array.length;
        int[][] rotatedArray = new int[width][height];

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                rotatedArray[j][height-1-i] = array[i][j];
            }
        }
        return rotatedArray;
    }
}
