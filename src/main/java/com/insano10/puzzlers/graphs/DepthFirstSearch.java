package com.insano10.puzzlers.graphs;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSearch
{
    public static List<Node> dfsRecursive(Node startNode)
    {
        List<Node> visitedNodes = new ArrayList<>();

        doRecursiveDfs(startNode, visitedNodes);

        return visitedNodes;
    }

    private static void doRecursiveDfs(Node node, List<Node> visitedNodes)
    {
        if(!node.isVisited())
        {
            node.markVisited();
            visitedNodes.add(node);
            for (Node neighbour : node.getNeighbours())
            {
                doRecursiveDfs(neighbour, visitedNodes);
            }
        }
    }
}
