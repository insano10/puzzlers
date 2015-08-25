package com.insano10.puzzlers.trees;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BinaryTreeTest
{
    /*
                        root
                  2             3
               4      5      6     7
            8

     */
    private final Node<String> root = new Node<>("root");
    private final Node<String> node2 = new Node<>("2");
    private final Node<String> node3 = new Node<>("3");
    private final Node<String> node4 = new Node<>("4");
    private final Node<String> node5 = new Node<>("5");
    private final Node<String> node6 = new Node<>("6");
    private final Node<String> node7 = new Node<>("7");
    private final Node<String> node8 = new Node<>("8");
    private final BinaryTree<String> tree = new BinaryTree<>(root);

    @Before
    public void setUp() throws Exception
    {
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);
        node4.setLeft(node8);
    }

    @Test
    public void shouldTraverseBinaryTreePreorder() throws Exception
    {
        List<String> visitedNodes = new ArrayList<>();

        tree.traversePreorder(visitedNodes::add);

        assertThat(visitedNodes.get(0)).isEqualTo(root.data);
        assertThat(visitedNodes.get(1)).isEqualTo(node2.data);
        assertThat(visitedNodes.get(2)).isEqualTo(node4.data);
        assertThat(visitedNodes.get(3)).isEqualTo(node8.data);
        assertThat(visitedNodes.get(4)).isEqualTo(node5.data);
        assertThat(visitedNodes.get(5)).isEqualTo(node3.data);
        assertThat(visitedNodes.get(6)).isEqualTo(node6.data);
        assertThat(visitedNodes.get(7)).isEqualTo(node7.data);
    }

    @Test
    public void shouldTraverseBinaryTreeInorder() throws Exception
    {
        List<String> visitedNodes = new ArrayList<>();

        tree.traverseInorder(visitedNodes::add);

        assertThat(visitedNodes.get(0)).isEqualTo(node8.data);
        assertThat(visitedNodes.get(1)).isEqualTo(node4.data);
        assertThat(visitedNodes.get(2)).isEqualTo(node2.data);
        assertThat(visitedNodes.get(3)).isEqualTo(node5.data);
        assertThat(visitedNodes.get(4)).isEqualTo(root.data);
        assertThat(visitedNodes.get(5)).isEqualTo(node6.data);
        assertThat(visitedNodes.get(6)).isEqualTo(node3.data);
        assertThat(visitedNodes.get(7)).isEqualTo(node7.data);

    }

    @Test
    public void shouldTraverseBinaryTreePostorder() throws Exception
    {
        List<String> visitedNodes = new ArrayList<>();

        tree.traversePostorder(visitedNodes::add);

        assertThat(visitedNodes.get(0)).isEqualTo(node8.data);
        assertThat(visitedNodes.get(1)).isEqualTo(node4.data);
        assertThat(visitedNodes.get(2)).isEqualTo(node5.data);
        assertThat(visitedNodes.get(3)).isEqualTo(node2.data);
        assertThat(visitedNodes.get(4)).isEqualTo(node6.data);
        assertThat(visitedNodes.get(5)).isEqualTo(node7.data);
        assertThat(visitedNodes.get(6)).isEqualTo(node3.data);
        assertThat(visitedNodes.get(7)).isEqualTo(root.data);

    }
}