package com.insano10.puzzlers.heaps;

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
    private static final int ROOT_INDEX = 1;
    private final int maxSize;

    private Comparable<T>[] tree;
    private int currentNodeCount = 0;

    public MinHeap()
    {
        this(100);
    }

    public MinHeap(int initialSize)
    {
        this(initialSize, 1024);
    }

    public MinHeap(int initialSize, int maxSize)
    {
        //+1 as the array index starts at 1
        this.tree = new Comparable[initialSize + 1];
        this.maxSize = maxSize;
    }

    public void add(T element)
    {
        if(currentNodeCount == maxSize)
        {
            throw new IllegalStateException("Heap is full [" + currentNodeCount + "]. Cannot add more elements");
        }

        currentNodeCount++;

        if(currentNodeCount >= tree.length)
        {
            tree = resizeHeap(currentNodeCount);
        }

        tree[currentNodeCount] = element;

        if(currentNodeCount > 1)
        {
            int parentIdx = getParentIndex(currentNodeCount);
            heapify(parentIdx);
        }
    }

    private Comparable[] resizeHeap(int currentNodeCount)
    {
        Comparable[] resizedArray = new Comparable[currentNodeCount + 1];

        for (int i = 0; i < tree.length; i++)
        {
            resizedArray[i] = tree[i];
        }

        return resizedArray;
    }

    /*
    move the last element inserted into the root position, removing the current root element
    heapify from the root
     */
    public T extract()
    {
        T root = elementAt(ROOT_INDEX);

        if(currentNodeCount > 0)
        {
            tree[ROOT_INDEX] = elementAt(currentNodeCount);
            tree[currentNodeCount] = null;
            currentNodeCount--;

            heapify(ROOT_INDEX);
        }
        return root;
    }

    public T peekRoot()
    {
        return elementAt(ROOT_INDEX);
    }

    private void heapify(int heapRootIdx)
    {
        //find the smallest of the root and it's 2 children
        int leftIdx = (2 * heapRootIdx);
        int rightIdx = (2 * heapRootIdx) + 1;

        Optional<Integer> smallestIndex = whichIndexContainsTheSmallestElement(leftIdx, rightIdx);
        smallestIndex = smallestIndex.flatMap((leftRightSmallest) -> whichIndexContainsTheSmallestElement(leftRightSmallest, heapRootIdx));

        int smallestIdx = smallestIndex.orElse(heapRootIdx);


        //if the smallest is not the root, swap that element with the root and heapify again from the next parent
        //to bubble up the element
        if(smallestIdx != heapRootIdx)
        {
            T tmp = elementAt(smallestIdx);
            tree[smallestIdx] = elementAt(heapRootIdx);
            tree[heapRootIdx] = tmp;

            int parentIndex = getParentIndex(heapRootIdx);
            if(parentIndex > 0)
            {
                heapify(parentIndex);
            }
        }
    }

    private T elementAt(int idx)
    {
        if(idx > currentNodeCount)
        {
            return null;
        }
        return (T)tree[idx];
    }

    private int getParentIndex(final int childIndex)
    {
        return (int)Math.floor(childIndex /2);
    }

    private Optional<Integer> whichIndexContainsTheSmallestElement(int idxA, int idxB)
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
        return a.compareTo(b) <= 0 ? Optional.of(idxA) : Optional.of(idxB);
    }
}
