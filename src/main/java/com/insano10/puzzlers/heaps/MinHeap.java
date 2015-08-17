package com.insano10.puzzlers.heaps;

import java.lang.reflect.Array;
import java.util.Optional;

//todo: make a generic heap class and give it a predicate to tell it what flavour of heap it is (e.g. min/max)
//Not threadsafe
public class MinHeap<T extends Comparable<T>>
{
    /*
        Storing a binary tree in an array is fast but wastes space in sparse trees and is expensive to grow.
        This makes it best for complete trees that do not change often.

        If a node has an index i, its children are found at:
        - indices 2i + 1 (for the left child)
        - indices 2i + 2 (for the right)

        While its parent (if any) is found at index floor(i/2) (assuming the root has index zero)
     */

    private Comparable<T>[] tree;
    private int currentNodeCount = 0;

    public MinHeap()
    {
        this(100);
    }

    public MinHeap(int initialSize)
    {
        tree = new Comparable[initialSize];
    }

    public void add(T element)
    {
        tree[currentNodeCount] = element;

        if(currentNodeCount > 0)
        {
            int parentIdx = (int)Math.floor(currentNodeCount/2);
            heapify(parentIdx);
        }

        currentNodeCount++;
    }

    /*
    move the last element inserted into the root position, removing the current root element
    heapify from the root
     */
    public T extract()
    {
        T root = elementAt(0);

        if(currentNodeCount > 0)
        {
            tree[0] = elementAt(currentNodeCount - 1);
            tree[currentNodeCount - 1] = null;
            currentNodeCount--;

            heapify(0);
        }
        return root;
    }

    public T peekRoot()
    {
        return null;
    }

    private void heapify(int heapRootIdx)
    {
        //find the largest of the root and it's 2 children
        int leftIdx = (2 * heapRootIdx) + 1;
        int rightIdx = (2 * heapRootIdx) + 2;

        Optional<Integer> largestIndex = whichIndexContainsTheLargestElement(leftIdx, rightIdx);
        largestIndex = largestIndex.flatMap((leftRightLargest) -> whichIndexContainsTheLargestElement(leftRightLargest, heapRootIdx));

        int largestIdx = largestIndex.orElse(heapRootIdx);


        //if the largest is not the root, swap that element with the root and heapify again from the largest
        //to bubble down the element
        if(largestIdx != heapRootIdx)
        {
            T tmp = elementAt(largestIdx);
            tree[largestIdx] = elementAt(heapRootIdx);
            tree[heapRootIdx] = tmp;

            heapify(largestIdx);
        }
    }

    private T elementAt(int idx)
    {
        return (T)tree[idx];
    }

    private Optional<Integer> whichIndexContainsTheLargestElement(int idxA, int idxB)
    {
        T a = elementAt(idxA);
        T b = elementAt(idxB);

        //todo: replace with something more functional?
        if(a == null && b == null)
        {
            return Optional.empty();
        }
        if(a == null)
        {
            return Optional.of(idxB);
        }
        if(b == null)
        {
            return Optional.of(idxA);
        }
        return a.compareTo(b) >= 0 ? Optional.of(idxA) : Optional.of(idxB);
    }
}
