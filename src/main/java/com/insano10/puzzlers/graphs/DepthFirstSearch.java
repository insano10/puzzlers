package com.insano10.puzzlers.graphs;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
            visitNode(node, visitedNodes);
            for (Node neighbour : node.getNeighbours())
            {
                doRecursiveDfs(neighbour, visitedNodes);
            }
        }
    }

    public static List<Node> dfsIterative(Node startNode)
    {
        Stack<Node> nodesToVisit = new Stack<>();
        List<Node> visitedNodes = new ArrayList<>();

        nodesToVisit.push(startNode);

        while(!nodesToVisit.isEmpty())
        {
            Node node = nodesToVisit.pop();

            if(!node.isVisited())
            {
                visitNode(node, visitedNodes);

                //note: the reversal is not necessary and is only there to maintain visit order with the recursive version
                for (Node neighbour : Lists.reverse(node.getNeighbours()))
                {
                    nodesToVisit.push(neighbour);
                }
            }
        }

        return visitedNodes;
    }

    private static void visitNode(Node startNode, List<Node> visitedNodes)
    {
        startNode.markVisited();
        visitedNodes.add(startNode);
    }
}
