package com.insano10.puzzlers.graphs;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;


public class DepthFirstSearchTest
{
    private Node n1 = new Node("n1");
    private Node n2 = new Node("n2");
    private Node n3 = new Node("n3");
    private Node n4 = new Node("n4");
    private Node n5 = new Node("n5");
    private Node n6 = new Node("n6");
    private Node n7 = new Node("n7");

    private Edge e1 = new Edge(1, n1, n2, 1);
    private Edge e2 = new Edge(2, n1, n3, 1);
    private Edge e3 = new Edge(3, n2, n4, 1);
    private Edge e4 = new Edge(4, n3, n5, 1);
    private Edge e5 = new Edge(5, n4, n5, 1);
    private Edge e6 = new Edge(6, n5, n6, 1);
    private Edge e7 = new Edge(7, n5, n7, 1);

    @Before
    public void setUp() throws Exception
    {
        /*
                       - n2 -------- n4
                n1----|               |   --- n6
                       - n3 -------- n5 -|
                                          --- n7
         */

        n1.addEdges(e1, e2);
        n2.addEdges(e1, e3);
        n3.addEdges(e2, e4);
        n4.addEdges(e3, e5);
        n5.addEdges(e4, e5, e6, e7);
        n6.addEdges(e6);
        n7.addEdges(e7);
    }

    @Test
    public void shouldTraverseNodesUsingDepthFirstSearchRecursive() throws Exception
    {
        List<Node> visitedNodes = new ArrayList<>();
        List<Node> pathToNode = new ArrayList<>();
        DepthFirstSearch.dfsRecursive(n1, n7, visitedNodes::add, pathToNode::addAll);

        assertThat(visitedNodes.get(0)).isEqualTo(n1);
        assertThat(visitedNodes.get(1)).isEqualTo(n2);
        assertThat(visitedNodes.get(2)).isEqualTo(n4);
        assertThat(visitedNodes.get(3)).isEqualTo(n5);
        assertThat(visitedNodes.get(4)).isEqualTo(n3);
        assertThat(visitedNodes.get(5)).isEqualTo(n6);
        assertThat(visitedNodes.get(6)).isEqualTo(n7);

        assertThat(pathToNode.get(0)).isEqualTo(n1);
        assertThat(pathToNode.get(1)).isEqualTo(n2);
        assertThat(pathToNode.get(2)).isEqualTo(n4);
        assertThat(pathToNode.get(3)).isEqualTo(n5);
        assertThat(pathToNode.get(4)).isEqualTo(n7);
        //NOTE: this is not the shortest path to 7, DFS will give an answer but not necessarily the best one
    }

    @Test
    public void shouldTraverseNodesUsingDepthFirstSearchIterative() throws Exception
    {
        List<Node> visitedNodes = new ArrayList<>();
        List<Node> pathToNode = new ArrayList<>();
        DepthFirstSearch.dfsIterative(n1, n7, visitedNodes::add, pathToNode::addAll);

        assertThat(visitedNodes.get(0)).isEqualTo(n1);
        assertThat(visitedNodes.get(1)).isEqualTo(n3);
        assertThat(visitedNodes.get(2)).isEqualTo(n5);
        assertThat(visitedNodes.get(3)).isEqualTo(n7);

        assertThat(pathToNode.get(0)).isEqualTo(n1);
        assertThat(pathToNode.get(1)).isEqualTo(n3);
        assertThat(pathToNode.get(2)).isEqualTo(n5);
        assertThat(pathToNode.get(3)).isEqualTo(n7);
    }
}