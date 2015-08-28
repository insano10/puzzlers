package com.insano10.puzzlers.graphs;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GraphColouringTest
{
    /*

    Given a graph G, is it possible to colour it with 2 colours and not have
    any edge where both vertices are the same colour?

     */

    /*
                        [e] -- [f] -- [g]
                        |              |
                [a] -- [b] -- [d]     [h]
                        |              |
                       [c] ------------
     */
    @Test
    public void shouldColourSuccessfully() throws Exception
    {
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        Node f = new Node("f");
        Node g = new Node("g");
        Node h = new Node("h");
        List<Node> nodes = Lists.newArrayList(a, b, c, d, e, f, g, h);

        Edge e1 = new Edge(1, a, b, 1);
        Edge e2 = new Edge(2, b, e, 1);
        Edge e3 = new Edge(3, b, d, 1);
        Edge e4 = new Edge(4, b, c, 1);
        Edge e5 = new Edge(5, e, f, 1);
        Edge e6 = new Edge(6, f, g, 1);
        Edge e7 = new Edge(7, g, h, 1);
        Edge e8 = new Edge(8, h, c, 1);
        List<Edge> edges = Lists.newArrayList(e1, e2, e3, e4, e5, e6, e7, e8);

        a.addEdges(e1);
        b.addEdges(e1, e2, e3, e4);
        c.addEdges(e4, e8);
        d.addEdges(e3);
        e.addEdges(e2, e5);
        f.addEdges(e5, e6);
        g.addEdges(e6, e7);
        h.addEdges(e7, e8);

        Graph graph = new Graph(nodes, edges);

        GraphColouring graphColouring = new GraphColouring(graph);
        assertThat(graphColouring.canBeColouredWithTwoColours()).isTrue();
    }


    /*
                    [e] --------- [f]
                    |              |
            [a] -- [b] -- [d] --- [g]
                    |              |
                   [c] ------------
   */
    @Test
    public void shouldFailToColour() throws Exception
    {
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        Node f = new Node("f");
        Node g = new Node("g");
        List<Node> nodes = Lists.newArrayList(a, b, c, d, e, f, g);

        Edge e1 = new Edge(1, a, b, 1);
        Edge e2 = new Edge(2, b, e, 1);
        Edge e3 = new Edge(3, b, d, 1);
        Edge e4 = new Edge(4, b, c, 1);
        Edge e5 = new Edge(5, e, f, 1);
        Edge e6 = new Edge(6, f, g, 1);
        Edge e7 = new Edge(7, g, d, 1);
        Edge e8 = new Edge(8, g, c, 1);
        List<Edge> edges = Lists.newArrayList(e1, e2, e3, e4, e5, e6, e7, e8);

        a.addEdges(e1);
        b.addEdges(e1, e2, e3, e4);
        c.addEdges(e4, e8);
        d.addEdges(e3, e7);
        e.addEdges(e2, e5);
        f.addEdges(e5, e6);
        g.addEdges(e6, e7, e8);

        Graph graph = new Graph(nodes, edges);

        GraphColouring graphColouring = new GraphColouring(graph);
        assertThat(graphColouring.canBeColouredWithTwoColours()).isFalse();
    }
}
