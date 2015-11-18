package com.insano10.puzzlers.puzzles.codility.countingElements;

import java.util.HashSet;
import java.util.Set;

public class PermCheck
{
    public int solution(int[] input)
    {
        int maxVal = input.length;
        Set<Integer> found = new HashSet<>();

        for(int i=0 ; i<input.length ; i++)
        {
            if(input[i] <= maxVal)
            {
                found.add(input[i]);
            }
        }

        return found.size() == maxVal ? 1 : 0;
    }
}
