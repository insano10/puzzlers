package com.insano10.puzzlers.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BreadthFirstSearch
{
    public static void bfs(Node startNode, Node endNode, Consumer<Node> callback)
    {
        List<Node> nodesToVisit = new ArrayList<>();

        nodesToVisit.add(startNode);

        while(!nodesToVisit.isEmpty())
        {
            Node node = nodesToVisit.remove(0);

            if(!node.isVisited())
            {
                visitNode(node, callback);

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
    }

    private static void visitNode(Node node, Consumer<Node> callback)
    {
        node.markVisited();
        callback.accept(node);
    }
}
