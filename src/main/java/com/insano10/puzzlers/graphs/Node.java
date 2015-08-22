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
    public String toString()
    {
        return "Node{" +
                "neighbours=" + neighbours.size() +
                ", name='" + name + '\'' +
                ", visited=" + visited +
                '}';
    }
}
