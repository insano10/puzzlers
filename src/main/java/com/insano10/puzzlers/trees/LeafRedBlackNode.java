package com.insano10.puzzlers.trees;

final class LeafRedBlackNode<T> implements RedBlackNode<T>
{
    @Override
    public RedBlackTree.Colour getColour()
    {
        return RedBlackTree.Colour.BLACK;
    }

    @Override
    public void setColour(RedBlackTree.Colour colour)
    {

    }

    @Override
    public RedBlackNode<T> getParent()
    {
        return null;
    }

    @Override
    public void setLeft(RedBlackNode<T> node)
    {

    }

    @Override
    public void setRight(RedBlackNode<T> node)
    {

    }

    @Override
    public boolean isLeaf()
    {
        return true;
    }

    @Override
    public T getData()
    {
        return null;
    }

    @Override
    public RedBlackNode<T> getLeft()
    {
        return null;
    }

    @Override
    public RedBlackNode<T> getRight()
    {
        return null;
    }
}
