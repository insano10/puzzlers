package com.insano10.puzzlers.puzzles.codility.countingElements;

import java.util.HashSet;
import java.util.Set;

public class FrogRiverOne
{
    public int solution(int X, int[] input)
    {
        Set<Integer> found = new HashSet<>();

        for(int i=0 ; i<input.length ; i++)
        {
            if(input[i] <= X)
            {
                found.add(input[i]);
            }

            if(found.size() == X)
            {
                return i;
            }
        }

        return -1;
    }
}
