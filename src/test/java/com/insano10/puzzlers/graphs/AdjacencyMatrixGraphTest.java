package com.insano10.puzzlers.graphs;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AdjacencyMatrixGraphTest
{
    @Test
    public void shouldStoreEdgeBetweenNodes() throws Exception
    {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(3);

        graph.addEdgeBetween(0,1);
        graph.addEdgeBetween(1,1);
        graph.addEdgeBetween(1,2);
        graph.addEdgeBetween(2,0);

        assertThat(graph.isEdgeBetween(0,1)).isTrue();
        assertThat(graph.isEdgeBetween(1,1)).isTrue();
        assertThat(graph.isEdgeBetween(1,2)).isTrue();
        assertThat(graph.isEdgeBetween(2,0)).isTrue();
        assertThat(graph.isEdgeBetween(0,0)).isFalse();
        assertThat(graph.isEdgeBetween(1,0)).isFalse();
        assertThat(graph.isEdgeBetween(0,2)).isFalse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowEdgesBetweenNonExistentNodes() throws Exception
    {

        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(3);

        graph.addEdgeBetween(0, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotGetEdgeBetweenNonExistentNodes() throws Exception
    {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(3);

        graph.isEdgeBetween(0,4);
    }
}