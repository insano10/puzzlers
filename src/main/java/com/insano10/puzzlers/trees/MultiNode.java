package com.insano10.puzzlers.trees;

import java.util.HashMap;
import java.util.Map;

public class MultiNode<T>
{
    final T data;
    final Map<T, MultiNode<T>> children = new HashMap<>();
    boolean terminal;

    public static <T> MultiNode<T> of(T data)
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
        children.put(child.data, child);
    }

    public boolean hasChild(T data)
    {
        return children.containsKey(data);
    }

    public MultiNode<T> getChild(T data)
    {
        return children.get(data);
    }
}
