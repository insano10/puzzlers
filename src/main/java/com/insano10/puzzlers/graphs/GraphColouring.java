package com.insano10.puzzlers.graphs;

import java.util.HashMap;
import java.util.Map;

public class GraphColouring
{
    private final Graph graph;
    private final Map<Node, Colour> nodeColours = new HashMap<>();

    private enum Colour
    {
        RED, BLUE;
    }

    public GraphColouring(Graph graph)
    {
        this.graph = graph;
    }

    public boolean canBeColouredWithTwoColours()
    {
        //colour the first node RED
        return colourNode(graph.getNodes().get(0), Colour.RED, Colour.BLUE);
    }

    private boolean colourNode(Node node, Colour colour1, Colour colour2)
    {
        boolean valid = true;

        //store the fact that we have coloured this node with colour1
        nodeColours.put(node, colour1);

        for(Edge edge : node.getEdges())
        {
            //for every neighbouring node
            Node neighbour = edge.getOppositeNode(node);

            //if the neighbour is already coloured
            if(nodeColours.containsKey(neighbour))
            {
                //and the colour matches colour1
                if(nodeColours.get(neighbour).equals(colour1))
                {
                    //we have failed
                    return false;
                }
            }
            else
            {
                //if it isn't coloured, then colour it the opposite colour (colour2)
                valid = colourNode(neighbour, colour2, colour1);

                //if this failed then don't bother trying to colour the other nodes
                if(!valid) break;
            }
        }

        return valid;
    }
}
