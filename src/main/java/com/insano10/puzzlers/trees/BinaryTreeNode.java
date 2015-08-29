package com.insano10.puzzlers.trees;

public interface BinaryTreeNode<T>
{
    T getData();

    BinaryTreeNode<T> getLeft();

    BinaryTreeNode<T> getRight();
}
