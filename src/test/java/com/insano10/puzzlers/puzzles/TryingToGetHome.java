package com.insano10.puzzlers.puzzles;

import com.insano10.puzzlers.graphs.DijkstraSearch;
import com.insano10.puzzlers.graphs.Edge;
import com.insano10.puzzlers.graphs.Graph;
import com.insano10.puzzlers.graphs.Node;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TryingToGetHome
{
    /*
            [o][o][H][o][o][o][o][o][o][o]
            [o][o][o][o][o][o][o][o][o][o]
            [o][o][o][o][x][o][o][o][o][o]
            [o][o][o][o][x][x][o][o][o][o]
            [o][x][x][x][x][o][o][o][o][o]
            [o][o][o][o][o][o][o][o][o][o]
            [o][o][o][o][o][x][x][o][o][o]
            [o][o][o][o][o][x][x][o][o][o]
            [o][o][o][o][o][o][o][o][o][o]
            [o][o][o][o][o][o][o][o][M][o]

            On the given 10x10 grid there is a guy at M trying to reach his house at H.
            He can move through any square that is not blocked. A blocked square contains an 'x'
            a free square contains a 'o'.

            Find the shortest path home.
     */

    @Test
    public void shouldFindShortestPathHome() throws Exception
    {
        //all nodes
        List<Node> nodes = new ArrayList<>();
        Node[][] nodeArray = new Node[10][10];
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (coordinateHasNode(i, j))
                {
                    Node node = new Node(String.format("node[%d,%d", i, j));
                    nodeArray[i][j] = node;
                    nodes.add(node);
                }
            }
        }

        //all edges
        List<Edge> edges = new ArrayList<>();
        int edgeId = 0;
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                maybeAddEdgeBetween(i - 1, j, i, j, nodeArray, edges, edgeId++);
                maybeAddEdgeBetween(i + 1, j, i, j, nodeArray, edges, edgeId++);
                maybeAddEdgeBetween(i, j - 1, i, j, nodeArray, edges, edgeId++);
                maybeAddEdgeBetween(i, j + 1, i, j, nodeArray, edges, edgeId++);
            }
        }

        Graph graph = new Graph(nodes, edges);
        List<Node> pathHome = new ArrayList<>();

        DijkstraSearch.dijkstra(graph, nodeArray[8][0], nodeArray[2][9], System.out::println, pathHome::addAll);

        System.out.println("Path home is:");
        for (Node node : pathHome)
        {
            System.out.println(node);
        }

        /*

            [o][o][p][p][o][o][o][o][o][o]
            [o][o][o][p][p][p][o][o][o][o]
            [o][o][o][o][x][p][p][o][o][o]
            [o][o][o][o][x][x][p][o][o][o]
            [o][x][x][x][x][o][p][p][o][o]
            [o][o][o][o][o][o][o][p][p][o]
            [o][o][o][o][o][x][x][o][p][o]
            [o][o][o][o][o][x][x][o][p][o]
            [o][o][o][o][o][o][o][o][p][o]
            [o][o][o][o][o][o][o][o][p][o]

            output: 16 moves
         */
    }

    private boolean coordinateHasNode(int x, int y)
    {
        //these are all the coordinates with 'x' in them
        if ((x == 5 && y == 2) ||
                (x == 6 && y == 2) ||
                (x == 5 && y == 3) ||
                (x == 6 && y == 3) ||
                (x == 1 && y == 5) ||
                (x == 2 && y == 5) ||
                (x == 3 && y == 5) ||
                (x == 4 && y == 5) ||
                (x == 4 && y == 6) ||
                (x == 5 && y == 6) ||
                (x == 4 && y == 7))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    private void maybeAddEdgeBetween(int x1, int y1, int x2, int y2, Node[][] nodeArray, List<Edge> edges, int edgeId)
    {
        if ((x1 >= 0 && x1 < 10 && y1 >= 0 && y1 < 10) &&
                (x2 >= 0 && x2 < 10 && y2 >= 0 && y2 < 10) &&
                nodeArray[x1][y1] != null && nodeArray[x2][y2] != null)
        {
            edges.add(new Edge(edgeId, nodeArray[x1][y1], nodeArray[x2][y2], 1));
        }
    }
}
