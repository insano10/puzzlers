package com.insano10.puzzlers.heaps;

//todo: make a generic heap class and give it a predicate to tell it what flavour of heap it is (e.g. min/max)
//Not threadsafe
public class MinHeap<T extends Comparable<T>>
{
    /*
        Storing a binary tree in an array is fast but wastes space in sparse trees and is expensive to grow.
        This makes it best for complete trees that do not change often.

        If a node has an index i, its children are found at:
        - indices 2i (for the left child)
        - indices 2i + 1 (for the right)

        While its parent (if any) is found at index floor(i/2) (assuming the root has index zero)
     */

    private Comparable<T>[] tree;
    private int currentNodeCount = 0;
    private int[] gaps;

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
        //todo: need to fill gaps
        if(currentNodeCount == 0)
        {
            tree[currentNodeCount] = element;
        }
        else
        {
            int comparison = tree[currentNodeCount].compareTo(element);

            int elementIdx = (comparison < 0) ?
                    (2 * currentNodeCount) + 1 : //right child
                    (2 * currentNodeCount);      //left child

                tree[elementIdx] = element;
        }
        currentNodeCount++;
    }

    public T extract()
    {
        return null;
    }

    public T peekRoot()
    {
        return null;
    }
}
