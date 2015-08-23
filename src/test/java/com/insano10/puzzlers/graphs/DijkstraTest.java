package com.insano10.puzzlers.graphs;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DijkstraTest
{

        /*
                n1 ------(4)------- n2 -----(1)---- n3
                |                   |               |
               (1)                 (1)             (2)
                |                   |               |
                n4 ------(1)-------n5 -----(2)----- n6
         */

    Node n1 = new Node("n1");
    Node n2 = new Node("n2");
    Node n3 = new Node("n3");
    Node n4 = new Node("n4");
    Node n5 = new Node("n5");
    Node n6 = new Node("n6");
    Graph graph;

    @Before
    public void setUp() throws Exception
    {
        List<Node> nodes = new ArrayList<>();
        nodes.add(n1);
        nodes.add(n2);
        nodes.add(n3);
        nodes.add(n4);
        nodes.add(n5);
        nodes.add(n6);

        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, n1, n2, 4));
        edges.add(new Edge(1, n1, n4, 1));
        edges.add(new Edge(2, n2, n3, 1));
        edges.add(new Edge(3, n2, n5, 1));
        edges.add(new Edge(4, n3, n6, 2));
        edges.add(new Edge(5, n4, n5, 1));
        edges.add(new Edge(6, n5, n6, 2));

        graph = new Graph(nodes, edges);
    }

    @Test
    public void shouldTraverseWeightedNodesUsingDijkstrasAlgorithm() throws Exception
    {
        List<Node> visitedNodes = new ArrayList<>();
        List<Node> pathToEndNode = new ArrayList<>();

        DijkstraSearch.dijkstra(graph, n1, n6, visitedNodes::add, pathToEndNode::addAll);

        assertThat(visitedNodes.get(0)).isEqualTo(n1);
        assertThat(visitedNodes.get(1)).isEqualTo(n4);
        assertThat(visitedNodes.get(2)).isEqualTo(n5);
        assertThat(visitedNodes.get(3)).isEqualTo(n2);
        assertThat(visitedNodes.get(4)).isEqualTo(n3);
        assertThat(visitedNodes.get(5)).isEqualTo(n6);

        assertThat(pathToEndNode.get(0)).isEqualTo(n1);
        assertThat(pathToEndNode.get(1)).isEqualTo(n4);
        assertThat(pathToEndNode.get(2)).isEqualTo(n5);
        assertThat(pathToEndNode.get(3)).isEqualTo(n6);
    }
}