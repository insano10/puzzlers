package com.insano10.puzzlers.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

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

    public void dfs(int startingNode, Consumer<Integer> onVisitCallback)
    {
        doDfs(startingNode, onVisitCallback, new HashSet<>());
    }

    private void doDfs(int startingNode, Consumer<Integer> onVisitCallback, Set<Integer> visited)
    {
        visited.add(startingNode);
        onVisitCallback.accept(startingNode);

        int[] edgesFromNode = edges[startingNode];

        for(int i=0 ; i<numNodes ; i++)
        {
            if(edgesFromNode[i] > 0 && !visited.contains(i))
            {
                doDfs(i, onVisitCallback, visited);
            }
        }
    }

    public void bfs(int startingNode, Consumer<Integer> onVisitCallback)
    {
        Set<Integer> visited = new HashSet<>();
        List<Integer> toVisit = new ArrayList<>();
        toVisit.add(startingNode);

        while(!toVisit.isEmpty())
        {
            int node = toVisit.remove(0);

            if(!visited.contains(node))
            {
                onVisitCallback.accept(node);
                visited.add(node);

                int[] edgesFromNode = edges[node];

                for(int i=0 ; i<numNodes ; i++)
                {
                    if(edgesFromNode[i] > 0 && !visited.contains(i))
                    {
                        toVisit.add(i);
                    }
                }
            }
        }
    }

    private void validateNodeIndices(int fromNode, int toNode)
    {
        if (fromNode > numNodes || toNode > numNodes)
        {
            throw new IllegalArgumentException("Max node index: " + numNodes);
        }
    }
}
