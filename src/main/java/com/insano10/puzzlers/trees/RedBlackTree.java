package com.insano10.puzzlers.trees;

import java.util.function.Consumer;

import static com.insano10.puzzlers.trees.RedBlackTree.Colour.RED;
import static com.insano10.puzzlers.trees.RedBlackTree.Colour.BLACK;

public class RedBlackTree<T extends Comparable<T>> implements BinaryTree<T>
{
    public enum Colour
    {
        RED, BLACK;
    }

    private RedBlackNode<T> root = null;

    //purely for starting a traversal. In hindsight the public traverse methods should not take a node
    public RedBlackNode<T> getRoot()
    {
        return root;
    }

    public void insert(T data)
    {
        binaryTreeInsert(data);
    }

    @Override
    public void traversePreorder(BinaryTreeNode<T> node, Consumer<T> onVisit)
    {
        if(node != null)
        {
            onVisit.accept(node.getData());
            traversePreorder(node.getLeft(), onVisit);
            traversePreorder(node.getRight(), onVisit);
        }
    }

    @Override
    public void traverseInorder(BinaryTreeNode<T> node, Consumer<T> onVisit)
    {
        if(node != null)
        {
            traverseInorder(node.getLeft(), onVisit);
            onVisit.accept(node.getData());
            traverseInorder(node.getRight(), onVisit);
        }
    }

    @Override
    public void traversePostorder(BinaryTreeNode<T> node, Consumer<T> onVisit)
    {
        if(node != null)
        {
            traversePostorder(node.getLeft(), onVisit);
            traversePostorder(node.getRight(), onVisit);
            onVisit.accept(node.getData());
        }
    }


    private void binaryTreeInsert(T data)
    {
        if(root == null)
        {
            root = new RedBlackNode<>(data, BLACK);
        }
        else
        {
            RedBlackNode<T> next = root;

            while(true)
            {
                if(data.compareTo(next.getData()) <= 0)
                {
                    if(next.getLeft() == null)
                    {
                        next.setLeft(new RedBlackNode<T>(data, RED));
                        break;
                    }
                    next = next.getLeft();
                }
                else
                {
                    if(next.getRight() == null)
                    {
                        next.setRight(new RedBlackNode<T>(data, RED));
                        break;
                    }
                    next = next.getRight();
                }
            }
        }
    }
}
