package com.insano10.puzzlers.trees;

import java.util.*;

public class MultiNode<T extends Comparable<T>>
{
    final T data;
    final Map<T, MultiNode<T>> childMap = new HashMap<>();
    final List<MultiNode<T>> children = new ArrayList<>();
    boolean terminal;

    public static <T extends Comparable<T>> MultiNode<T> of(T data)
    {
        return new MultiNode<>(data);
    }

    private MultiNode(T data)
    {
        this.data = data;
    }

    public void terminal()
    {
        this.terminal = true;
    }

    public boolean isTerminal()
    {
        return terminal;
    }

    public void addChild(MultiNode<T> child)
    {
        children.add(child);
        childMap.put(child.data, child);

        //this is really inefficient but as its an up front cost when creating the Trie I don't care too much atm
        Collections.sort(children, ((n1, n2) -> {

            if (n1 == null)
            {
                return -1;
            }
            if (n2 == null)
            {
                return 1;
            }
            return n1.data.compareTo(n2.data);

        }));

        Collections.reverse(children);
    }

    public boolean hasChildWithValue(T data)
    {
        return childMap.containsKey(data);
    }

    public MultiNode<T> getChild(T data)
    {
        return childMap.get(data);
    }

    public List<MultiNode<T>> getChildrenInDescendingOrder()
    {
        return children;
    }
}
