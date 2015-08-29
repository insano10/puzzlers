package com.insano10.puzzlers.trees;

import java.util.function.Consumer;

public class RecursiveBinaryTree<T> implements BinaryTree<T>
{
    public void traversePreorder(Node<T> root, Consumer<T> onVisit)
    {
        if(root != null)
        {
            onVisit.accept(root.getData());
            traversePreorder(root.getLeft(), onVisit);
            traversePreorder(root.getRight(), onVisit);
        }
    }

    public void traverseInorder(Node<T> root, Consumer<T> onVisit)
    {
        if(root != null)
        {
            traverseInorder(root.getLeft(), onVisit);
            onVisit.accept(root.getData());
            traverseInorder(root.getRight(), onVisit);
        }
    }

    public void traversePostorder(Node<T> root, Consumer<T> onVisit)
    {
        if(root != null)
        {
            traversePostorder(root.getLeft(), onVisit);
            traversePostorder(root.getRight(), onVisit);
            onVisit.accept(root.getData());
        }
    }
}
