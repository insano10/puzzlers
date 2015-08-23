package com.insano10.puzzlers.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph
{
    private final List<Node> nodes;
    private final List<Edge> edges;
    private final Map<Node, List<Edge>> nodeEdges = new HashMap<>();

    public Graph(List<Node> nodes, List<Edge> edges)
    {
        this.nodes = nodes;
        this.edges = edges;

        for (Node node : nodes)
        {
            nodeEdges.put(node, new ArrayList<>());
        }

        for (Edge edge : edges)
        {
            nodeEdges.get(edge.getFrom()).add(edge);

            if(edge.isBiDirectional())
            {
                nodeEdges.get(edge.getTo()).add(edge);
            }
        }
    }

    public List<Node> getNodes()
    {
        return nodes;
    }

    public List<Edge> getEdgesFrom(Node node)
    {
        return nodeEdges.get(node);
    }

    public int getCost(Node from, Node to)
    {
        for (Edge edge : nodeEdges.get(from))
        {
            if(edge.getTo().equals(to))
            {
                return edge.getWeight();
            }
        }
        return Integer.MAX_VALUE;
    }
}
