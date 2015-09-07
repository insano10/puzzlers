package com.insano10.puzzlers.graphs;

public class WeightedAdjacencyMatrixGraph
{
    private final int[][] edges;
    private int numNodes;

    public WeightedAdjacencyMatrixGraph(int numNodes)
    {
        this.numNodes = numNodes;
        this.edges = new int[numNodes][numNodes];
    }

    public void addEdgeBetween(int fromNode, int toNode, int weight)
    {
        validateNodeIndices(fromNode, toNode);

        edges[fromNode][toNode] = weight;
    }

    public boolean isEdgeBetween(int fromNode, int toNode)
    {
        validateNodeIndices(fromNode, toNode);

        return edges[fromNode][toNode] > 0;
    }

    public int getEdgeWeight(int fromNode, int toNode)
    {
        validateNodeIndices(fromNode, toNode);

        return edges[fromNode][toNode];
    }

    private void validateNodeIndices(int fromNode, int toNode)
    {
        if (fromNode > numNodes || toNode > numNodes)
        {
            throw new IllegalArgumentException("Max node index: " + numNodes);
        }
    }
}
