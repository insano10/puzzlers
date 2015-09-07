package com.insano10.puzzlers.graphs;

public class AdjacencyMatrixGraph
{
    private final boolean[][] edges;
    private int numNodes;

    public AdjacencyMatrixGraph(int numNodes)
    {
        this.numNodes = numNodes;
        this.edges = new boolean[numNodes][numNodes];
    }

    public void addEdgeBetween(int fromNode, int toNode)
    {
        validateNodeIndices(fromNode, toNode);

        edges[fromNode][toNode] = true;
    }

    public boolean isEdgeBetween(int fromNode, int toNode)
    {
        validateNodeIndices(fromNode, toNode);

        return edges[fromNode][toNode];
    }

    private void validateNodeIndices(int fromNode, int toNode)
    {
        if(fromNode > numNodes || toNode > numNodes)
        {
            throw new IllegalArgumentException("Max node index: " + numNodes);
        }
    }
}
