package com.insano10.puzzlers.sets;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;

public class PowersetTest
{
    @Test
    public void shouldGeneratePowersetOfASetContainingMoreThan1Element() throws Exception
    {
        Set<Integer> set = newHashSet(1, 2, 3, 4); // powerset should have 2^size elements

        Set<Set<Integer>> powerset = newHashSet( //16
                newHashSet(),
                newHashSet(1),
                newHashSet(2),
                newHashSet(3),
                newHashSet(4),
                newHashSet(1, 2),
                newHashSet(1, 3),
                newHashSet(1, 4),
                newHashSet(2, 3),
                newHashSet(2, 4),
                newHashSet(3, 4),
                newHashSet(1, 2, 3),
                newHashSet(2, 3, 4),
                newHashSet(1, 2, 4),
                newHashSet(1, 3, 4),
                newHashSet(1, 2, 3, 4));

        assertThat(Powerset.of(set)).containsOnlyElementsOf(powerset);
    }

    @Test
    public void shouldGeneratePowersetOfASetContainingSets() throws Exception
    {
        Set<Set<Integer>> set = newHashSet(
                newHashSet(1),
                newHashSet(2),
                newHashSet(3));

        Set<Set<Set<Integer>>> powerset = newHashSet(); //8
        powerset.add(newHashSet(newHashSet()));

        addSetWithSingleElementToPowersetOfSets(powerset, 1);
        addSetWithSingleElementToPowersetOfSets(powerset, 2);
        addSetWithSingleElementToPowersetOfSets(powerset, 3);
        powerset.add(newHashSet(newHashSet(1), newHashSet(2)));
        powerset.add(newHashSet(newHashSet(2), newHashSet(3)));
        powerset.add(newHashSet(newHashSet(1), newHashSet(3)));
        powerset.add(newHashSet(newHashSet(1), newHashSet(2), newHashSet(3)));


        assertThat(Powerset.of(set)).containsOnlyElementsOf(powerset);
    }

    @Ignore
    @Test
    public void shouldGeneratePowersetOfAnEmptySet() throws Exception
    {
        Set<Integer> set = newHashSet();

        Set<Set<Integer>> powerset = newHashSet(newHashSet()); //1

        assertThat(Powerset.of(set)).containsOnlyElementsOf(powerset);

    }

    @Ignore
    @Test
    public void shouldGeneratePowersetOfSetContainingTheEmptySet() throws Exception
    {
        Set<Set<Integer>> set = newHashSet(newHashSet());

        Set<Set<Set<Integer>>> powerset = newHashSet(); //2
        powerset.add(newHashSet());
        powerset.add(newHashSet(newHashSet()));

        assertThat(Powerset.of(set)).containsOnlyElementsOf(powerset);
    }

    private void addSetWithSingleElementToPowersetOfSets(Set<Set<Set<Integer>>> powersetOfSets, int setSingleElement)
    {
        /*
        It looks like you cannot create a triple nested collection with a single element as types cannot be inferred correctly
        So take the long way round
         */
        HashSet<Set<Integer>> subset = new HashSet<>();
        subset.add(newHashSet(setSingleElement));
        powersetOfSets.add(subset);
    }
}