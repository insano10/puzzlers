package com.insano10.puzzlers.trees;

import java.util.function.Consumer;

public interface BinaryTree<T>
{
    void traversePreorder(BinaryTreeNode<T> node, Consumer<T> onVisit);

    void traverseInorder(BinaryTreeNode<T> node, Consumer<T> onVisit);

    void traversePostorder(BinaryTreeNode<T> node, Consumer<T> onVisit);
}
