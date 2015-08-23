package com.insano10.puzzlers.graphs;

public class Edge
{
    private final int id;
    private final Node from;
    private final Node to;
    private final int weight;
    private final boolean bidirectional = true;

    public Edge(int id, Node from, Node to, int weight)
    {
        this.id = id;
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public Node getFrom()
    {
        return from;
    }

    public Node getTo()
    {
        return to;
    }

    public int getWeight()
    {
        return weight;
    }

    public boolean isBiDirectional()
    {
        return bidirectional;
    }

    public Node getOppositeNode(Node node)
    {
        return node.equals(from) ? to :
                node.equals(to) ? from : null;
    }
}
