package com.insano10.puzzlers.trees;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RedBlackTreeTest
{
    /*

            5                                  5B
         1     10           =>              1B      10B
            7      20                            7R     20R
     */
    @Test
    public void shouldInsertNodes() throws Exception
    {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        tree.insert(5);
        tree.insert(1);
        tree.insert(10);
        tree.insert(20);
        tree.insert(7);

        List<Integer> visitOrder = new ArrayList<>();
        tree.visitInOrder(visitOrder::add);

        assertThat(visitOrder.get(0)).isEqualTo(1);
        assertThat(visitOrder.get(1)).isEqualTo(5);
        assertThat(visitOrder.get(2)).isEqualTo(7);
        assertThat(visitOrder.get(3)).isEqualTo(10);
        assertThat(visitOrder.get(4)).isEqualTo(20);
    }

    /*
            1                                   2B
              2                             1B        4B
                3           =>                    3R     5R
                  4
                    5
     */
    @Test
    public void shouldResultInBalancedTreeAfterInsertion() throws Exception
    {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);

        List<Integer> visitOrder = new ArrayList<>();
        tree.visitInOrder(visitOrder::add);

        assertThat(visitOrder.get(0)).isEqualTo(1);
        assertThat(visitOrder.get(1)).isEqualTo(2);
        assertThat(visitOrder.get(2)).isEqualTo(3);
        assertThat(visitOrder.get(3)).isEqualTo(4);
        assertThat(visitOrder.get(4)).isEqualTo(5);

        RedBlackNode<Integer> root = tree.getRoot();

        assertThat(root.getData()).isEqualTo(2);
        assertThat(root.getLeft().getData()).isEqualTo(1);
        assertThat(root.getRight().getData()).isEqualTo(4);
        assertThat(root.getRight().getLeft().getData()).isEqualTo(3);
        assertThat(root.getRight().getRight().getData()).isEqualTo(5);

    }
}