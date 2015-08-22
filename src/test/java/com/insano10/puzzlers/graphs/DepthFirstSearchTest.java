package com.insano10.puzzlers.graphs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class DepthFirstSearchTest
{
    @Parameterized.Parameters(name = "Implementation: {0}")
    public static Collection<Object[]> data()
    {
        return Arrays.<Object[]>asList(
                new Object[]{"Recursive", (BiFunction<Node, Node, List<Node>>)DepthFirstSearch::dfsRecursive},
                new Object[]{"Iterative", (BiFunction<Node, Node, List<Node>>)DepthFirstSearch::dfsIterative}
        );
    }

    private final BiFunction<Node, Node, List<Node>> dfsAlgorithm;

    public DepthFirstSearchTest(String name, BiFunction<Node, Node, List<Node>> dfsAlgorithm)
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

        List<Node> nodesInVisitOrder = dfsAlgorithm.apply(n1, n7);

        assertThat(nodesInVisitOrder.get(0)).isEqualTo(n1);
        assertThat(nodesInVisitOrder.get(1)).isEqualTo(n2);
        assertThat(nodesInVisitOrder.get(2)).isEqualTo(n4);
        assertThat(nodesInVisitOrder.get(3)).isEqualTo(n5);
        assertThat(nodesInVisitOrder.get(4)).isEqualTo(n3);
        assertThat(nodesInVisitOrder.get(5)).isEqualTo(n6);
        assertThat(nodesInVisitOrder.get(6)).isEqualTo(n7);
    }
}