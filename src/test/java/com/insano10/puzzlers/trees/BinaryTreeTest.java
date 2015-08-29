package com.insano10.puzzlers.trees;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
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
    private final BinaryTree<String> tree;

    @Parameterized.Parameters(name = "Implementation: {0}")
    public static Collection<Object[]> data()
    {
        return Arrays.<Object[]>asList(
                new Object[]{"Iterative", (BinaryTree) new IterativeBinaryTree<>()},
                new Object[]{"Recursive", (BinaryTree) new RecursiveBinaryTree<>()}
        );
    }

    public BinaryTreeTest(String name, BinaryTree<String> tree)
    {
        this.tree = tree;
    }

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

        tree.traversePreorder(root, visitedNodes::add);

        assertThat(visitedNodes.get(0)).isEqualTo(root.getData());
        assertThat(visitedNodes.get(1)).isEqualTo(node2.getData());
        assertThat(visitedNodes.get(2)).isEqualTo(node4.getData());
        assertThat(visitedNodes.get(3)).isEqualTo(node8.getData());
        assertThat(visitedNodes.get(4)).isEqualTo(node5.getData());
        assertThat(visitedNodes.get(5)).isEqualTo(node3.getData());
        assertThat(visitedNodes.get(6)).isEqualTo(node6.getData());
        assertThat(visitedNodes.get(7)).isEqualTo(node7.getData());
    }

    @Test
    public void shouldTraverseBinaryTreeInorder() throws Exception
    {
        List<String> visitedNodes = new ArrayList<>();

        tree.traverseInorder(root, visitedNodes::add);

        assertThat(visitedNodes.get(0)).isEqualTo(node8.getData());
        assertThat(visitedNodes.get(1)).isEqualTo(node4.getData());
        assertThat(visitedNodes.get(2)).isEqualTo(node2.getData());
        assertThat(visitedNodes.get(3)).isEqualTo(node5.getData());
        assertThat(visitedNodes.get(4)).isEqualTo(root.getData());
        assertThat(visitedNodes.get(5)).isEqualTo(node6.getData());
        assertThat(visitedNodes.get(6)).isEqualTo(node3.getData());
        assertThat(visitedNodes.get(7)).isEqualTo(node7.getData());

    }

    @Test
    public void shouldTraverseBinaryTreePostorder() throws Exception
    {
        List<String> visitedNodes = new ArrayList<>();

        tree.traversePostorder(root, visitedNodes::add);

        assertThat(visitedNodes.get(0)).isEqualTo(node8.getData());
        assertThat(visitedNodes.get(1)).isEqualTo(node4.getData());
        assertThat(visitedNodes.get(2)).isEqualTo(node5.getData());
        assertThat(visitedNodes.get(3)).isEqualTo(node2.getData());
        assertThat(visitedNodes.get(4)).isEqualTo(node6.getData());
        assertThat(visitedNodes.get(5)).isEqualTo(node7.getData());
        assertThat(visitedNodes.get(6)).isEqualTo(node3.getData());
        assertThat(visitedNodes.get(7)).isEqualTo(root.getData());

    }
}