package com.insano10.puzzlers.graphs;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node
{
    private final String name;
    private final List<Edge> edges = new ArrayList<>();
    private boolean visited;

    public Node(String name)
    {
        this.name = name;
        this.visited = false;
    }

    public void addEdges(Edge... theEdges)
    {
        edges.addAll(Arrays.asList(theEdges));
    }

    public List<Edge> getEdges()
    {
        return ImmutableList.copyOf(edges);
    }

    public String getName()
    {
        return name;
    }

    public void markVisited()
    {
        visited = true;
    }

    public void clearVisited()
    {
        visited = false;
    }

    public boolean isVisited()
    {
        return visited;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return name.equals(node.name);

    }

    @Override
    public int hashCode()
    {
        return name.hashCode();
    }

    @Override
    public String toString()
    {
        return "Node{" +
                "edges=" + edges.size() +
                ", name='" + name + '\'' +
                ", visited=" + visited +
                '}';
    }
}
