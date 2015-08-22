package com.insano10.puzzlers.graphs;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch
{
    public static List<Node> dfsRecursive(Node startNode, Node endNode)
    {
        List<Node> visitedNodes = new ArrayList<>();

        doRecursiveDfs(startNode, endNode, visitedNodes);

        return visitedNodes;
    }

    private static void doRecursiveDfs(Node node, Node endNode, List<Node> visitedNodes)
    {
        if(!node.isVisited() && !endNode.isVisited())
        {
            visitNode(node, visitedNodes);

            if(!node.equals(endNode))
            {
                for (Node neighbour : node.getNeighbours())
                {
                    doRecursiveDfs(neighbour, endNode, visitedNodes);
                }
            }
        }
    }

    public static List<Node> dfsIterative(Node startNode, Node endNode)
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

                if(endNode.isVisited())
                {
                    break;
                }

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
