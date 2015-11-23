package com.insano10.puzzlers.puzzles.codility.stacksandqueues;

import java.util.Stack;

public class StoneWall
{
    public int solution(int[] input)
    {
        Stack<Integer> wallHeights = new Stack<>();

        int blockCount = 1;
        wallHeights.push(input[0]);

        for (int i = 1; i < input.length; i++)
        {
            if(input[i] < input[i-1])
            {
                //curr block is lower

                //find the last block to build upon
                while(!wallHeights.isEmpty() && wallHeights.peek() > input[i])
                {
                    wallHeights.pop();
                }

                //maybe add a new block for any additional height
                if(wallHeights.isEmpty() || wallHeights.peek() < input[i])
                {
                    wallHeights.push(input[i]);
                    blockCount++;
                }
            }
            else if(input[i] == input[i-1])
            {
                //curr block is equal height
            }
            else
            {
                //curr block is higher
                wallHeights.push(input[i]);
                blockCount++;
            }
        }

        return blockCount;
    }
}
