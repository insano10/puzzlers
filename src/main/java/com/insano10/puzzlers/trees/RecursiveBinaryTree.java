package com.insano10.puzzlers.trees;

import java.util.function.Consumer;

public class RecursiveBinaryTree<T> implements BinaryTree<T>
{
    public void traversePreorder(Node<T> root, Consumer<T> onVisit)
    {
        if(root != null)
        {
            onVisit.accept(root.data);
            traversePreorder(root.left, onVisit);
            traversePreorder(root.right, onVisit);
        }
    }

    public void traverseInorder(Node<T> root, Consumer<T> onVisit)
    {
        if(root != null)
        {
            traverseInorder(root.left, onVisit);
            onVisit.accept(root.data);
            traverseInorder(root.right, onVisit);
        }
    }

    public void traversePostorder(Node<T> root, Consumer<T> onVisit)
    {
        if(root != null)
        {
            traversePostorder(root.left, onVisit);
            traversePostorder(root.right, onVisit);
            onVisit.accept(root.data);
        }
    }
}
