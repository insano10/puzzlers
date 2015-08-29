package com.insano10.puzzlers.trees;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RedBlackTreeTest
{
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
        tree.traverseInorder(tree.getRoot(), visitOrder::add);

        assertThat(visitOrder.get(0)).isEqualTo(1);
        assertThat(visitOrder.get(1)).isEqualTo(5);
        assertThat(visitOrder.get(2)).isEqualTo(7);
        assertThat(visitOrder.get(3)).isEqualTo(10);
        assertThat(visitOrder.get(4)).isEqualTo(20);
    }
}