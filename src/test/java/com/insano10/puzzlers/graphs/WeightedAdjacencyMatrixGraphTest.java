package com.insano10.puzzlers.graphs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WeightedAdjacencyMatrixGraphTest
{
    @Test
    public void shouldStoreEdgeBetweenNodes() throws Exception
    {
        WeightedAdjacencyMatrixGraph graph = new WeightedAdjacencyMatrixGraph(3);

        graph.addEdgeBetween(0, 1, 1);
        graph.addEdgeBetween(1, 1, 2);
        graph.addEdgeBetween(1, 2, 3);
        graph.addEdgeBetween(2, 0, 4);

        assertThat(graph.isEdgeBetween(0, 1)).isTrue();
        assertThat(graph.isEdgeBetween(1, 1)).isTrue();
        assertThat(graph.isEdgeBetween(1, 2)).isTrue();
        assertThat(graph.isEdgeBetween(2, 0)).isTrue();
        assertThat(graph.isEdgeBetween(0, 0)).isFalse();
        assertThat(graph.isEdgeBetween(1, 0)).isFalse();
        assertThat(graph.isEdgeBetween(0, 2)).isFalse();

        assertThat(graph.getEdgeWeight(0, 1)).isEqualTo(1);
        assertThat(graph.getEdgeWeight(1, 1)).isEqualTo(2);
        assertThat(graph.getEdgeWeight(1, 2)).isEqualTo(3);
        assertThat(graph.getEdgeWeight(2, 0)).isEqualTo(4);
        assertThat(graph.getEdgeWeight(0, 0)).isEqualTo(0);
        assertThat(graph.getEdgeWeight(1, 0)).isEqualTo(0);
        assertThat(graph.getEdgeWeight(0, 2)).isEqualTo(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowEdgesBetweenNonExistentNodes() throws Exception
    {

        WeightedAdjacencyMatrixGraph graph = new WeightedAdjacencyMatrixGraph(3);

        graph.addEdgeBetween(0, 4, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotGetEdgeBetweenNonExistentNodes() throws Exception
    {
        WeightedAdjacencyMatrixGraph graph = new WeightedAdjacencyMatrixGraph(3);

        graph.isEdgeBetween(0, 4);
    }

    /*

        0 ----- 1 ------ 2
        |       |        |
        3 ----- 4 ------ 5

     */
    @Test
    public void shouldDoDfs() throws Exception
    {
        WeightedAdjacencyMatrixGraph graph = new WeightedAdjacencyMatrixGraph(6);

        graph.addEdgeBetween(0, 1, 1);
        graph.addEdgeBetween(0, 3, 1);
        graph.addEdgeBetween(1, 2, 1);
        graph.addEdgeBetween(1, 4, 1);
        graph.addEdgeBetween(2, 5, 1);
        graph.addEdgeBetween(3, 4, 1);
        graph.addEdgeBetween(4, 5, 1);

        List<Integer> visitedNodes = new ArrayList<>();

        graph.dfs(0, (node) -> visitedNodes.add(node));

        assertThat(visitedNodes.get(0)).isEqualTo(0);
        assertThat(visitedNodes.get(1)).isEqualTo(1);
        assertThat(visitedNodes.get(2)).isEqualTo(2);
        assertThat(visitedNodes.get(3)).isEqualTo(5);
        assertThat(visitedNodes.get(4)).isEqualTo(4);
        assertThat(visitedNodes.get(5)).isEqualTo(3);

    }

    /*

        0 ----- 1 ------ 2
        |       |        |
        3 ----- 4 ------ 5

     */
    @Test
    public void shouldDoBfs() throws Exception
    {
        WeightedAdjacencyMatrixGraph graph = new WeightedAdjacencyMatrixGraph(6);

        graph.addEdgeBetween(0, 1, 1);
        graph.addEdgeBetween(0, 3, 1);
        graph.addEdgeBetween(1, 2, 1);
        graph.addEdgeBetween(1, 4, 1);
        graph.addEdgeBetween(2, 5, 1);
        graph.addEdgeBetween(3, 4, 1);
        graph.addEdgeBetween(4, 5, 1);

        List<Integer> visitedNodes = new ArrayList<>();

        graph.bfs(0, (node) -> visitedNodes.add(node));

        assertThat(visitedNodes.get(0)).isEqualTo(0);
        assertThat(visitedNodes.get(1)).isEqualTo(1);
        assertThat(visitedNodes.get(2)).isEqualTo(3);
        assertThat(visitedNodes.get(3)).isEqualTo(2);
        assertThat(visitedNodes.get(4)).isEqualTo(4);
        assertThat(visitedNodes.get(5)).isEqualTo(5);

    }
}