package com.insano10.puzzlers.graphs;

import java.util.*;
import java.util.function.Consumer;

public class BreadthFirstSearch
{
    public static void bfs(Node startNode, Node endNode, Consumer<Node> onVisited, Consumer<List<Node>> onFound)
    {
        List<Node> nodesToVisit = new ArrayList<>();
        Map<Node, Node> paths = new HashMap<>();

        nodesToVisit.add(startNode);

        while (!nodesToVisit.isEmpty())
        {
            Node node = nodesToVisit.remove(0);

            if (!node.isVisited())
            {
                visitNode(node, onVisited);

                if (endNode.isVisited())
                {
                    onFound.accept(reconstructPath(startNode, endNode, paths));
                    break;
                }

                for(Edge edge : node.getEdges())
                {
                    Node neighbour = edge.getOppositeNode(node);
                    if(!neighbour.isVisited())
                    {
                        paths.putIfAbsent(neighbour, node);
                        nodesToVisit.add(neighbour);
                    }
                }
            }
        }
    }

    private static void visitNode(Node node, Consumer<Node> onVisited)
    {
        node.markVisited();
        onVisited.accept(node);
    }

    private static List<Node> reconstructPath(Node startNode, Node endNode, Map<Node, Node> paths)
    {
        List<Node> path = new ArrayList<>();
        path.add(endNode);
        Node node = endNode;

        while(!node.equals(startNode))
        {
            node = paths.get(node);
            path.add(node);
        }

        Collections.reverse(path);
        return path;
    }
}
