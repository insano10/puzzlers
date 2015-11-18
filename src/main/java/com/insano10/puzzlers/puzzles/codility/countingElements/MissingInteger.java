package com.insano10.puzzlers.puzzles.codility.countingElements;

import java.util.HashMap;
import java.util.Map;

public class MissingInteger
{
    public int solution(int[] input)
    {
        Map<Integer, Boolean> valueCount = new HashMap<>();

        for (int i = 0; i < input.length; i++)
        {
            if (input[i] > 0)
            {
                valueCount.put(input[i], true);
            }
        }

        for (int i = 1; i <= 100_000; i++)
        {
            if(!valueCount.getOrDefault(i, false))
            {
                return i;
            }
        }

        return 100_001;
    }
}
