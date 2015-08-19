package com.insano10.puzzlers.heaps;

import java.util.Comparator;
import java.util.Optional;

//todo: make a generic heap class and give it a predicate to tell it what flavour of heap it is (e.g. min/max)
//Not threadsafe
public class Heap<T extends Comparable<T>>
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
    private final Comparator<T> comparator;

    private Comparable<T>[] tree;
    private int currentNodeCount = 0;

    public Heap(Comparator<T> comparator)
    {
        this(100, comparator);
    }

    public Heap(int initialSize, Comparator<T> comparator)
    {
        this(initialSize, comparator, 1024);
    }

    public Heap(int initialSize, Comparator<T> comparator, int maxSize)
    {
        //+1 as the array index starts at 1
        this.tree = new Comparable[initialSize + 1];
        this.comparator = comparator;
        this.maxSize = maxSize;
    }

    public void add(T element)
    {
        if (currentNodeCount == maxSize)
        {
            throw new IllegalStateException("Heap is full [" + currentNodeCount + "]. Cannot add more elements");
        }

        currentNodeCount++;

        if (currentNodeCount >= tree.length)
        {
            tree = resizeHeap(currentNodeCount);
        }

        tree[currentNodeCount] = element;

        if (currentNodeCount > 1)
        {
            int parentIdx = getParentIndex(currentNodeCount);
            heapifyUp(parentIdx);
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
    public Optional<T> extract()
    {
        Optional<T> root = elementAt(ROOT_INDEX);

        if (root.isPresent())
        {
            tree[ROOT_INDEX] = elementAt(currentNodeCount).get();
            tree[currentNodeCount] = null;
            currentNodeCount--;

            heapifyDown(ROOT_INDEX);
        }
        return root;
    }

    public Optional<T> peekRoot()
    {
        return elementAt(ROOT_INDEX);
    }

    private void heapifyUp(int heapRootIdx)
    {
        int firstIdx = getIndexOfElementThatComesFirstInSubTree(heapRootIdx);

        //if the first element is not the root, swap that element with the root and heapify again from the next parent
        //to bubble up the values
        if (firstIdx != heapRootIdx)
        {
            swapElements(firstIdx, heapRootIdx);

            int parentIdx = getParentIndex(heapRootIdx);
            if(parentIdx > 0)
            {
                heapifyUp(parentIdx);
            }
        }
    }

    private void heapifyDown(int heapRootIdx)
    {
        int firstIdx = getIndexOfElementThatComesFirstInSubTree(heapRootIdx);

        //if the first element is not the root, swap that element with the root and heapify again from the element
        //to bubble down the values
        if (firstIdx != heapRootIdx)
        {
            swapElements(firstIdx, heapRootIdx);

            heapifyDown(firstIdx);
        }
    }

    private int getIndexOfElementThatComesFirstInSubTree(int heapRootIdx)
    {
        //find the element that should be at the top amongst the root and it's 2 children
        int leftIdx = (2 * heapRootIdx);
        int rightIdx = (2 * heapRootIdx) + 1;

        Optional<Integer> firstIndex = whichIndexContainsTheElementThatComesFirstInTheHeap(leftIdx, rightIdx);
        firstIndex = firstIndex.flatMap((leftRightFirst) -> whichIndexContainsTheElementThatComesFirstInTheHeap(leftRightFirst, heapRootIdx));

        return firstIndex.orElse(heapRootIdx);
    }

    private void swapElements(int idx1, int idx2)
    {
        T tmp = elementAt(idx1).get();
        tree[idx1] = elementAt(idx2).get();
        tree[idx2] = tmp;
    }

    private Optional<T> elementAt(int idx)
    {
        if (idx > currentNodeCount)
        {
            return Optional.empty();
        }
        return Optional.of((T) tree[idx]);
    }

    private int getParentIndex(final int childIndex)
    {
        return (int) Math.floor(childIndex / 2);
    }

    private Optional<Integer> whichIndexContainsTheElementThatComesFirstInTheHeap(int idxA, int idxB)
    {
        Optional<T> a = elementAt(idxA);
        Optional<T> b = elementAt(idxB);

        if(!a.isPresent() && !b.isPresent())
        {
            return Optional.empty();
        }
        if(!a.isPresent())
        {
            return Optional.of(idxB);
        }
        if(!b.isPresent())
        {
            return Optional.of(idxA);
        }
        return comparator.compare(a.get(), b.get()) <= 0 ? Optional.of(idxA) : Optional.of(idxB);
    }
}
