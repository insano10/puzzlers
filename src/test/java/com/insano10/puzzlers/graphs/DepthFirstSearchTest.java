package com.insano10.puzzlers.graphs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class DepthFirstSearchTest
{
    @Parameterized.Parameters(name = "Implementation: {0}")
    public static Collection<Object[]> data()
    {
        return Arrays.<Object[]>asList(
                new Object[]{"Recursive", (DFS) DepthFirstSearch::dfsRecursive},
                new Object[]{"Iterative", (DFS) DepthFirstSearch::dfsIterative}
        );
    }

    private interface DFS
    {
        void dfs(Node startNode, Node endNode, Consumer<Node> onVisited, Consumer<List<Node>> onFound);
    }

    private final DFS dfsAlgorithm;

    public DepthFirstSearchTest(String name, DFS dfsAlgorithm)
    {
        this.dfsAlgorithm = dfsAlgorithm;
    }

    @Test
    public void shouldTraverseNodesUsingDepthFirstSearch() throws Exception
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
        List<Node> pathToNode = new ArrayList<>();
        dfsAlgorithm.dfs(n1, n7, visitedNodes::add, pathToNode::addAll);

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
    }
}