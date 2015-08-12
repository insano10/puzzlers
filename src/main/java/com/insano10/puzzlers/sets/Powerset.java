package com.insano10.puzzlers.sets;

import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Powerset
{
    //todo: what happens if the set is massive? restrict input?
    /*
    Binary String implementation:

    Every element in the set will either be present or not present in a given subset
    All possible subsets can therefore be represented as a binary string.

    e.g.
        0000 = empty set
        0001 = set containing element 3
        1101 = set containing elements 0,2,and 3
     */
    public static <T> Set<Set<T>> of(Set<T> set)
    {
        final Set<Set<T>> powerSet = new HashSet<>();

        if(set.isEmpty())
        {
            powerSet.add(set);
            return powerSet;
        }

        final ArrayList<T> elementList = new ArrayList<>(set);

        long maxSubsets = (long) Math.pow(2, set.size());

        for (int i = 0; i < maxSubsets; i++)
        {
            String binaryString = Strings.padStart(Integer.toBinaryString(i), set.size(), '0');
            Set<T> subset = new HashSet<>();

            for (int bitIdx = 0; bitIdx < binaryString.length(); bitIdx++)
            {
                if(binaryString.charAt(bitIdx) == '1')
                {
                    subset.add(elementList.get(bitIdx));
                }
            }
            powerSet.add(subset);
        }

        return powerSet;
    }
}
