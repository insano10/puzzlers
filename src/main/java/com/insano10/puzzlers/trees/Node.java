package com.insano10.puzzlers.trees;

public class Node<T>
{
    private T data;
    private Node<T> left = null;
    private Node<T> right = null;

    public Node(T data)
    {
        this.data = data;
    }

    public void setLeft(Node<T> node)
    {
        this.left = node;
    }

    public void setRight(Node<T> node)
    {
        this.right = node;
    }

    public T getData()
    {
        return data;
    }

    public Node<T> getLeft()
    {
        return left;
    }

    public Node<T> getRight()
    {
        return right;
    }
}
