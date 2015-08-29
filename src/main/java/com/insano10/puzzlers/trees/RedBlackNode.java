package com.insano10.puzzlers.trees;

import com.insano10.puzzlers.trees.RedBlackTree.Colour;

public interface RedBlackNode<T> extends BinaryTreeNode<T>
{

    Colour getColour();
    void setColour(Colour colour);

    RedBlackNode<T> getParent();

    RedBlackNode<T> getLeft();
    RedBlackNode<T> getRight();

    void setLeft(RedBlackNode<T> node);
    void setRight(RedBlackNode<T> node);

    boolean isLeaf();
}
