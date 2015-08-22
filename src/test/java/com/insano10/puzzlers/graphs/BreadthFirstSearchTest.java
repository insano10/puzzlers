package com.insano10.puzzlers.graphs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;

import static org.assertj.core.api.Assertions.assertThat;

public class BreadthFirstSearchTest
{
    @Test
    public void shouldTraverseNodesUsingBreadthFirstSearch() throws Exception
    {

        /*
                       - n2 -------- n4
                n1----|               |   --- n6
                       - n3 -------- n5 -|
                                          --- n7
         */

        Node n1 = new Node("n1");
        Node n2 = new Node("n2");
        Node n3 = new Node("n3");
        Node n4 = new Node("n4");
        Node n5 = new Node("n5");
        Node n6 = new Node("n6");
        Node n7 = new Node("n7");

        n1.addNeighbours(n2,n3);
        n2.addNeighbours(n1, n4);
        n3.addNeighbours(n1, n5);
        n4.addNeighbours(n2, n5);
        n5.addNeighbours(n3, n4, n6, n7);
        n6.addNeighbours(n5);
        n7.addNeighbours(n5);

        List<Node> visitedNodes = new ArrayList<>();
        List<Node> pathToEndNode = new ArrayList<>();

        //search the whole graph
        BreadthFirstSearch.bfs(n1, n7, visitedNodes::add, pathToEndNode::addAll);

        assertThat(visitedNodes.get(0)).isEqualTo(n1);
        assertThat(visitedNodes.get(1)).isEqualTo(n2);
        assertThat(visitedNodes.get(2)).isEqualTo(n3);
        assertThat(visitedNodes.get(3)).isEqualTo(n4);
        assertThat(visitedNodes.get(4)).isEqualTo(n5);
        assertThat(visitedNodes.get(5)).isEqualTo(n6);
        assertThat(visitedNodes.get(6)).isEqualTo(n7);

        assertThat(pathToEndNode.get(0)).isEqualTo(n1);
        assertThat(pathToEndNode.get(1)).isEqualTo(n3);
        assertThat(pathToEndNode.get(2)).isEqualTo(n5);
        assertThat(pathToEndNode.get(3)).isEqualTo(n7);

        clear(visitedNodes);
        visitedNodes.clear();
        pathToEndNode.clear();

        //search a smaller bit
        BreadthFirstSearch.bfs(n4, n3, visitedNodes::add, pathToEndNode::addAll);

        assertThat(visitedNodes.get(0)).isEqualTo(n4);
        assertThat(visitedNodes.get(1)).isEqualTo(n2);
        assertThat(visitedNodes.get(2)).isEqualTo(n5);
        assertThat(visitedNodes.get(3)).isEqualTo(n1);
        assertThat(visitedNodes.get(4)).isEqualTo(n3);

        assertThat(pathToEndNode.get(0)).isEqualTo(n4);
        assertThat(pathToEndNode.get(1)).isEqualTo(n5);
        assertThat(pathToEndNode.get(2)).isEqualTo(n3);
    }

    private void clear(List<Node> nodes)
    {
        for (Node node : nodes)
        {
            node.clearVisited();
        }
    }
}