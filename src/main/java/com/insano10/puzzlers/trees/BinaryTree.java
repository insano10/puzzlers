package com.insano10.puzzlers.trees;

import java.util.function.Consumer;

public interface BinaryTree<T>
{
    void traversePreorder(Node<T> root, Consumer<T> onVisit);

    void traverseInorder(Node<T> root, Consumer<T> onVisit);

    void traversePostorder(Node<T> root, Consumer<T> onVisit);
}
