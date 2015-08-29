package com.insano10.puzzlers.trees;

import com.insano10.puzzlers.trees.RedBlackTree.Colour;

public class RedBlackNode<T> implements BinaryTreeNode<T>
{
    private final T data;
    private Colour colour;
    private RedBlackNode<T> left = null;
    private RedBlackNode<T> right = null;

    public RedBlackNode(T data, Colour colour)
    {
        this.data = data;
        this.colour = colour;
    }

    public void setLeft(RedBlackNode<T> node)
    {
        this.left = node;
    }

    public void setRight(RedBlackNode<T> node)
    {
        this.right = node;
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

    public Colour getColour()
    {
        return colour;
    }

    public void setColour(Colour colour)
    {
        this.colour = colour;
    }
}
