package com.insano10.puzzlers.trees;

public class Node<T>
{
    T data;
    Node<T> left = null;
    Node<T> right = null;

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
}
