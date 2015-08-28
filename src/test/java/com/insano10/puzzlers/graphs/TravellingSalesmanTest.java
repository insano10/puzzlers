package com.insano10.puzzlers.graphs;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TravellingSalesmanTest
{

    /*

                          [A]

                 [E]                 [B]
                     links between
                       all nodes

                    [D]        [C]


            Answer is: A-E-D-B-C-A
           [http://computing.dcu.ie/~humphrys/Notes/AI/statespace.html]
     */
    @Test
    public void shouldFindBestRouteInTinyGraph() throws Exception
    {
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        List<Node> nodes = Lists.newArrayList(a, b, c, d, e);

        Edge e1 = new Edge(1, a, b, 100);
        Edge e2 = new Edge(2, b, c, 50);
        Edge e3 = new Edge(3, c, d, 100);
        Edge e4 = new Edge(4, d, e, 50);
        Edge e5 = new Edge(5, e, a, 75);
        Edge e6 = new Edge(6, a, c, 300);
        Edge e7 = new Edge(7, a, d, 100);
        Edge e8 = new Edge(8, b, d, 75);
        Edge e9 = new Edge(9, b, e, 125);
        Edge e10 = new Edge(10, c, e, 125);
        List<Edge> edges = Lists.newArrayList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);

        a.addEdges(e1, e5, e6, e7);
        b.addEdges(e1, e2, e9, e9);
        c.addEdges(e2, e3, e6, e10);
        d.addEdges(e3, e4, e7, e8);
        e.addEdges(e4, e5, e9, e10);

        Graph graph = new Graph(nodes, edges);
        TravellingSalesman salesman = new TravellingSalesman();

        List<Node> route = salesman.getBestRoute(graph, a);

        assertThat(route).hasSize(6);
        assertThat(route.get(0)).isEqualTo(a);
        assertThat(route.get(1)).isEqualTo(e);
        assertThat(route.get(2)).isEqualTo(d);
        assertThat(route.get(3)).isEqualTo(b);
        assertThat(route.get(4)).isEqualTo(c);
        assertThat(route.get(5)).isEqualTo(a);
    }
}