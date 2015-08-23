package com.insano10.puzzlers.graphs;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node
{
    private final List<Node> neighbours = new ArrayList<>();
    private final String name;
    private boolean visited;

    public Node(String name)
    {
        this.name = name;
        this.visited = false;
    }

    public void addNeighbours(Node... nodes)
    {
        neighbours.addAll(Arrays.asList(nodes));
    }

    public List<Node> getNeighbours()
    {
        return ImmutableList.copyOf(neighbours);
    }

    public boolean hasUnvisitedNeighbours()
    {
        for (Node neighbour : neighbours)
        {
            if(!neighbour.isVisited())
            {
                return true;
            }
        }
        return false;
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
                "neighbours=" + neighbours.size() +
                ", name='" + name + '\'' +
                ", visited=" + visited +
                '}';
    }
}
