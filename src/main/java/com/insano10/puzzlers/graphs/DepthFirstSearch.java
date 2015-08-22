package com.insano10.puzzlers.graphs;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;

public class DepthFirstSearch
{
    public static void dfsRecursive(Node startNode, Node endNode, Consumer<Node> onVisited, Consumer<List<Node>> onFound)
    {
        List<Node> pathSoFar = new ArrayList<>();
        pathSoFar.add(startNode);
        doRecursiveDfs(startNode, endNode, pathSoFar, onVisited, onFound);
    }

    private static void doRecursiveDfs(Node node, Node endNode, List<Node> pathSoFar, Consumer<Node> onVisited, Consumer<List<Node>> onFound)
    {
        if (!node.isVisited() && !endNode.isVisited())
        {
            visitNode(node, onVisited);

            if (endNode.isVisited())
            {
                onFound.accept(pathSoFar);
            }
            else
            {
                for (Node neighbour : node.getNeighbours())
                {
                    ArrayList<Node> newPathSoFar = new ArrayList<>(pathSoFar);
                    newPathSoFar.add(neighbour);
                    doRecursiveDfs(neighbour, endNode, newPathSoFar, onVisited, onFound);
                }
            }
        }
    }

    public static void dfsIterative(Node startNode, Node endNode, Consumer<Node> onVisited, Consumer<List<Node>> onFound)
    {
        Stack<Node> nodesToVisit = new Stack<>();
        List<Node> pathSoFar = new ArrayList<>();

        nodesToVisit.push(startNode);

        while (!nodesToVisit.isEmpty())
        {
            Node node = nodesToVisit.pop();

            if (!node.isVisited())
            {
                pathSoFar.add(node);
                visitNode(node, onVisited);

                if (endNode.isVisited())
                {
                    onFound.accept(pathSoFar);
                    break;
                }

                if(node.hasUnvisitedNeighbours())
                {
                    //note: the reversal is not necessary and is only there to maintain visit order with the recursive version
                    for (Node neighbour : Lists.reverse(node.getNeighbours()))
                    {
                        nodesToVisit.push(neighbour);
                    }
                }
                else
                {
                    pathSoFar.remove(node);
                }
            }
        }
    }

    private static void visitNode(Node node, Consumer<Node> onVisited)
    {
        node.markVisited();
        onVisited.accept(node);
    }
}
