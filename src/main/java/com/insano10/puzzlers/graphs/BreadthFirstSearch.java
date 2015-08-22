package com.insano10.puzzlers.graphs;

import java.util.ArrayList;
import java.util.List;

public class BreadthFirstSearch
{

    public static List<Node> bfs(Node startNode, Node endNode)
    {
        List<Node> visitedNodes = new ArrayList<>();
        List<Node> nodesToVisit = new ArrayList<>();

        nodesToVisit.add(startNode);

        while(!nodesToVisit.isEmpty())
        {
            Node node = nodesToVisit.remove(0);

            if(!node.isVisited())
            {
                visitNode(node, visitedNodes);

                if(endNode.isVisited())
                {
                    break;
                }

                for (Node neighbour : node.getNeighbours())
                {
                    nodesToVisit.add(neighbour);
                }
            }
        }

        return visitedNodes;
    }

    private static void visitNode(Node node, List<Node> visitedNodes)
    {
        node.markVisited();
        visitedNodes.add(node);
    }
}
