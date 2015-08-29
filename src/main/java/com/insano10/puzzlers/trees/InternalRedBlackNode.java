package com.insano10.puzzlers.trees;

import com.insano10.puzzlers.trees.RedBlackTree.Colour;

public class InternalRedBlackNode<T> implements RedBlackNode<T>
{
    private final T data;
    private Colour colour;
    private RedBlackNode<T> left = new LeafRedBlackNode<>();
    private RedBlackNode<T> right = new LeafRedBlackNode<>();
    private RedBlackNode<T> parent;

    public InternalRedBlackNode(T data, Colour colour, RedBlackNode<T> parent)
    {
        this.data = data;
        this.colour = colour;
        this.parent = parent;
    }

    public void setLeft(RedBlackNode<T> node)
    {
        this.left = node;
    }

    public void setRight(RedBlackNode<T> node)
    {
        this.right = node;
    }

    @Override
    public boolean isLeaf()
    {
        return false;
    }

    public T getData()
    {
        return data;
    }

    public RedBlackNode<T> getLeft()
    {
        return left;
    }

    public RedBlackNode<T> getRight()
    {
        return right;
    }

    public RedBlackNode<T> getParent()
    {
        return parent;
    }

    @Override
    public Colour getColour()
    {
        return colour;
    }

    public void setColour(Colour colour)
    {
        this.colour = colour;
    }

}
