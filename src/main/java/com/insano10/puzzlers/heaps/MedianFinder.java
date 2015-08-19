package com.insano10.puzzlers.heaps;

import java.util.List;

public class MedianFinder
{
    public double getMedian(List<Integer> numbers)
    {
        Heap<Integer> minHeap = new Heap<>(Comparable::compareTo);
        Heap<Integer> maxHeap = new Heap<>((o1, o2) -> o2.compareTo(o1));

        if (numbers.isEmpty())
        {
            throw new IllegalArgumentException("Cannot find the median of no numbers");
        }

        if (numbers.size() == 1)
        {
            return numbers.get(0);
        }

        Integer e0 = numbers.get(0);
        Integer e1 = numbers.get(1);

        //add the smaller to the maxHeap and larger to the minHeap
        if (e0.compareTo(e1) <= 0)
        {
            maxHeap.add(e0);
            minHeap.add(e1);
        }
        else
        {
            maxHeap.add(e1);
            minHeap.add(e0);
        }

        for (int idx = 2; idx < numbers.size(); idx++)
        {
            addToAppropriateHeap(minHeap, maxHeap, numbers.get(idx));
            balanceHeaps(minHeap, maxHeap);
        }

        Integer largestSmallNumber = minHeap.peekRoot().get();
        Integer smallestLargeNumber = maxHeap.peekRoot().get();

        if (minHeap.size() == maxHeap.size())
        {
            return (largestSmallNumber + smallestLargeNumber) / 2d;
        }
        else
        {
            return minHeap.size() > maxHeap.size() ? largestSmallNumber : smallestLargeNumber;
        }

    }

    private void addToAppropriateHeap(Heap<Integer> minHeap, Heap<Integer> maxHeap, Integer element)
    {
        if (element < maxHeap.peekRoot().get())
        {
            maxHeap.add(element);
        }
        else
        {
            minHeap.add(element);
        }
    }

    private void balanceHeaps(Heap<Integer> minHeap, Heap<Integer> maxHeap)
    {
        if (minHeap.size() - maxHeap.size() > 1)
        {
            maxHeap.add(minHeap.extract().get());
        }
        else if (maxHeap.size() - minHeap.size() > 1)
        {
            minHeap.add(maxHeap.extract().get());
        }
    }
}
