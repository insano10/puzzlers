package com.insano10.puzzlers.graphs;

import java.util.*;
import java.util.function.Consumer;

public class DijkstraSearch
{
    public static void dijkstra(Graph graph, Node startNode, Node endNode, Consumer<Node> onVisited, Consumer<List<Node>> onFound)
    {
        Set<Node> workingNodes = new HashSet<>();
        List<Node> visitedNodes = new ArrayList<>();
        Map<Node, Node> pathSoFar = new HashMap<>();
        Map<Node, Integer> distances = new HashMap<>();

        //initialise known distances to a high value (i.e. we don't know the distance)
        for (Node node : graph.getNodes())
        {
            distances.put(node, Integer.MAX_VALUE);
        }

        //begin with the startNode (it is free to move to yourself)
        workingNodes.add(startNode);
        distances.put(startNode, 0);

        while (!workingNodes.isEmpty())
        {
            //visit nodes in order of increasing cost
            Node node = getUnvisitedNodeWithLowestKnownCost(workingNodes, distances);
            workingNodes.remove(node);
            visitedNodes.add(node);

            node.markVisited();
            onVisited.accept(node);

            if (endNode.isVisited())
            {
                onFound.accept(reconstructPath(pathSoFar, startNode, endNode));
                break;
            }

            //add neighbours to the working list if we can get to them more cheaply from here than
            //any node we have previously visited
            addNodeEdgeDistances(node, distances, workingNodes, visitedNodes, pathSoFar, graph);
        }

    }

    private static Node getUnvisitedNodeWithLowestKnownCost(Set<Node> nodes, Map<Node, Integer> distances)
    {
        int lowestCost = Integer.MAX_VALUE;
        Node lowestNode = null;
        for (Node node : nodes)
        {
            if (distances.containsKey(node))
            {
                if (distances.get(node) < lowestCost)
                {
                    lowestCost = distances.get(node);
                    lowestNode = node;
                }
            }
        }
        return lowestNode;
    }

    private static void addNodeEdgeDistances(Node node, Map<Node, Integer> distances, Set<Node> workingSet, List<Node> visited, Map<Node, Node> pathSoFar, Graph graph)
    {
        for (Edge edge : graph.getEdgesFrom(node))
        {
            Node neighbour = edge.getOppositeNode(node);

            if (!visited.contains(neighbour))
            {
                int newCost = distances.get(node) + edge.getWeight();

                if (newCost < distances.get(neighbour))
                {
                    distances.put(neighbour, newCost);
                    workingSet.add(neighbour);
                    pathSoFar.put(neighbour, node);
                }
            }
        }
    }

    private static List<Node> reconstructPath(Map<Node, Node> pathSoFar, Node startNode, Node endNode)
    {
        List<Node> path = new ArrayList<>();
        Node node = endNode;
        path.add(endNode);

        while (!node.equals(startNode))
        {
            node = pathSoFar.get(node);
            path.add(node);
        }
        Collections.reverse(path);
        return path;
    }

}
