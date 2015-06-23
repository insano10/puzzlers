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

    public static void main(String[] args)
    {
        int[][] array = createArray(10, 10);
        printArrayRotation(array);

        int[][] array2 = createArray(12, 10);
        printArrayRotation(array2);
    }

    private static void printArrayRotation(int[][] array)
    {
        printArray(array);

        array = rotate90Degrees(array);
        printArray(array);

        array = rotate90Degrees(array);
        printArray(array);

        array = rotate90Degrees(array);
        printArray(array);

        array = rotate90Degrees(array);
        printArray(array);
    }

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

    private static int[][] createArray(int width, int height)
    {
        int[][] array = new int[height][width];

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                array[i][j] = j + (i*height);
            }
        }
        return array;
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
            System.out.print("\n");
        }
        System.out.println();
        System.out.println();
    }
}
