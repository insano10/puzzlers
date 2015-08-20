package com.insano10.puzzlers.sorting;

import com.insano10.puzzlers.heaps.Heap;

public class HeapSort
{

    public static <T extends Comparable<T>> void sort(T[] elements)
    {
        if(elements == null)
        {
            throw new IllegalArgumentException("Cannot sort null array");
        }

        Heap<T> heap = buildMinHeap(elements);

        for (int i = 0; i < elements.length; i++)
        {
            elements[i] = heap.extract().get();
        }
    }

    private static <T extends Comparable<T>> Heap<T> buildMinHeap(T[] elements)
    {
        Heap<T> minHeap = new Heap<T>(Comparable::compareTo);

        for (T element : elements)
        {
            minHeap.add(element);
        }

        return minHeap;
    }
}
