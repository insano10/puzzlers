package com.insano10.puzzlers.graphs;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class TravellingSalesman
{
    public List<Node> getBestRoute(Graph graph, Node startingNode)
    {
        //we need to visit the starting node twice
        List<Node> nodes = new ArrayList<>(graph.getNodes());
        nodes.add(startingNode);

        //get a list of all possible permutations of the nodes in the graph (this is obviously really expensive)
        List<List<Node>> permutations = getPermutations(Lists.newArrayList(), nodes, startingNode);

        //work out the cost of each of these routes and choose the smallest
        int minRouteCost = Integer.MAX_VALUE;
        List<Node> minRoute = null;
        for (List<Node> route : permutations)
        {
            int routeCost = 0;
            Node previous = startingNode;
            for (Node node : route)
            {
                int cost = graph.getCost(previous, node);
                if(cost != Integer.MAX_VALUE)
                {
                    routeCost += cost;
                    previous = node;
                }
            }

            if(routeCost < minRouteCost)
            {
                minRouteCost = routeCost;
                minRoute = route;
            }
        }

        return minRoute;
    }

    /*

    round 1:     []  [A,B,C,D,E]
    A    B,C,D,E
    B    A,C,D,E
    C    A,B,D,E
    D    A,B,C,E

    round 2:        [A]  [B,C,D,E]
    A,B    C,D,E
    A,C    B,D,E
    A,D    B,C,E
    A,E    B,C,D

    ...
    round x:    [A,B,C,D,E]  []
    ...

     */
    private List<List<Node>> getPermutations(List<Node> prefix, List<Node> remaining, Node startingNode)
    {
        List<List<Node>> permutations = new ArrayList<>();

        if(remaining.size() == 0)
        {
            //only accept routes that start and end with the startingNode
            if(prefix.get(0).equals(startingNode) && prefix.get(prefix.size()-1).equals(startingNode))
            {
                permutations.add(prefix);
            }
        }
        else
        {
            //for as many nodes as are left in 'remaining', add each node to prefix and run again
            for (int i = 0; i < remaining.size(); i++)
            {
                List<Node> newPrefix = new ArrayList<>(prefix);
                newPrefix.add(remaining.get(i));

                List<Node> newRemaining = new ArrayList<>(remaining);
                newRemaining.remove(i);

                permutations.addAll(getPermutations(newPrefix, newRemaining, startingNode));
            }
        }

        return permutations;
    }
}
