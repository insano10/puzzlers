package com.insano10.puzzlers.heaps;

//todo: make a generic heap class and give it a predicate to tell it what flavour of heap it is (e.g. min/max)
public class MinHeap<T>
{
    /*
        Storing a binary tree in an array is fast but wastes space in sparse trees and is expensive to grow.
        This makes it best for complete trees that do not change often.

        If a node has an index i, its children are found at:
        - indices 2i + 1 (for the left child)
        - indices 2i + 2 (for the right)

        While its parent (if any) is found at index floor((i-1)/2) (assuming the root has index zero)
     */

    private Object[] tree;

    public MinHeap()
    {
        this(100);
    }

    public MinHeap(int initialSize)
    {
        tree = new Object[initialSize];
    }

    public void add(T element)
    {

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
