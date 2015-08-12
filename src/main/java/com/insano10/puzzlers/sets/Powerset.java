package com.insano10.puzzlers.sets;

import com.google.common.base.Strings;

import java.lang.reflect.Array;
import java.util.*;

import static com.google.common.collect.Sets.newHashSet;
import static com.google.common.collect.Sets.powerSet;

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

    Complexity: O(n2^n)
     */
    public static <T> Set<Set<T>> usingBinaryString(Set<T> set)
    {
        final Set<Set<T>> powerSet = new HashSet<>();

        if (set.isEmpty())
        {
            powerSet.add(set);
            return powerSet;
        }

        if (setContainsOnlyTheEmptySet(set))
        {
            powerSet.add(newHashSet());
            powerSet.add(set);
        }

        final ArrayList<T> elementList = new ArrayList<>(set);

        long maxSubsets = (long) Math.pow(2, set.size());

        for (int i = 0; i < maxSubsets; i++) //O(2^n)
        {
            String binaryString = Strings.padStart(Integer.toBinaryString(i), set.size(), '0');
            Set<T> subset = new HashSet<>();

            for (int bitIdx = 0; bitIdx < binaryString.length(); bitIdx++) //O(n)
            {
                if (binaryString.charAt(bitIdx) == '1')
                {
                    subset.add(elementList.get(bitIdx));
                }
            }
            powerSet.add(subset);
        }

        return powerSet;
    }

    /*

        Get the powerset of the tail until you reach size 1
        Then duplicate all sets in the powerset and add the head element to them all
       { 1,2,3 }
       { 1,2 }
       { 1 }        : { { 1 }, {} }
       => 2         : { { 1 }, {}, { 1, 2 }, { 2 } }
       => 3         : { { 1 }, {}, { 1, 2 }, { 2 }, { 1, 3 }, { 3 }, { 1, 2, 3 }, { 2, 3 } }

       Complexity: O(2^n) ? (as there are 2^n sets)
    */
    public static Set<Set> usingRecursiveCopyAndMerge(Set set)
    {
        return powersetOf(set.toArray());
    }

    private static Set<Set> powersetOf(Object[] elements)
    {
        Set<Set> powerSet = new HashSet<>();

        if(elements.length == 0)
        {
            //base case: empty set
            powerSet.add(newHashSet());
        }
        else if (elements.length == 1)
        {
            //base case: single element
            powerSet.add(newHashSet(elements[0]));
            powerSet.add(newHashSet());
        }
        else
        {
            //get the powerset of the tail
            Set<Set> tailPowerSet = powersetOf(Arrays.copyOfRange(elements, 0, Math.max(0, elements.length - 1)));
            powerSet.addAll(tailPowerSet);

            //now add the head element to a copy of each existing set
            for (Set set : tailPowerSet)
            {
                Set setCopy = new HashSet(set);
                setCopy.add(elements[elements.length - 1]);

                powerSet.add(setCopy);
            }
        }
        return powerSet;
    }

    private static <T> boolean setContainsOnlyTheEmptySet(Set<T> set)
    {
        if (set.size() == 1)
        {
            T element = set.iterator().next();
            if (element instanceof Set)
            {
                return ((Set) element).isEmpty();
            }
        }
        return false;
    }


}
