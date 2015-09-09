package com.insano10.puzzlers.sorting;

import java.util.Arrays;

public class MergeSort
{
    public static int[] sort(int[] array)
    {
        if(array.length <= 1)
        {
            return array;
        }

        int midIdx = array.length/2;


        int[] firstHalf = sort(Arrays.copyOfRange(array, 0, midIdx));
        int[] secondHalf = sort(Arrays.copyOfRange(array, midIdx, array.length));
        return merge(firstHalf, secondHalf);
    }

    private static int[] merge(int[] array1, int[] array2)
    {
        int[] merged = new int[array1.length + array2.length];

        int array1Idx = 0;
        int array2Idx = 0;
        for (int i = 0; i < merged.length; i++)
        {
            if(array1Idx == array1.length)
            {
                merged[i] = array2[array2Idx];
                array2Idx++;
            }
            else if(array2Idx == array2.length)
            {
                merged[i] = array1[array1Idx];
                array1Idx++;
            }
            else if(array1[array1Idx] < array2[array2Idx])
            {
                merged[i] = array1[array1Idx];
                array1Idx++;
            }
            else
            {
                merged[i] = array2[array2Idx];
                array2Idx++;
            }
        }
        return merged;
    }
}
