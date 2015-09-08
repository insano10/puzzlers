package com.insano10.puzzlers.sorting;

public class InsertionSort
{
    public static void insertionSort(int[] array)
    {
        for (int i = 1; i < array.length; i++)
        {
            boolean inserted = false;
            int currentElement = array[i];

            for (int j = (i - 1); j >= 0; j--)
            {
                if (currentElement < array[j])
                {
                    // shuffle previously sorted element to the right
                    array[j + 1] = array[j];
                }
                else
                {
                    // insert the element here
                    array[j + 1] = currentElement;
                    inserted = true;
                    break;
                }
            }

            if (!inserted)
            {
                array[0] = currentElement;
            }
        }
    }

}
