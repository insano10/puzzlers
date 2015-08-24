package com.insano10.puzzlers.trees;

import java.util.Optional;

public class Node<T>
{
    T data;
    Optional<Node<T>> left = Optional.empty();
    Optional<Node<T>> right = Optional.empty();

    public Node(T data)
    {
        this.data = data;
    }

    public void setLeft(Node<T> node)
    {
        this.left = Optional.of(node);
    }

    public void setRight(Node<T> node)
    {
        this.right = Optional.of(node);
    }
}
