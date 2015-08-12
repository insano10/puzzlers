package com.insano10.puzzlers.sets;

import com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class PowersetTest
{
    @Parameterized.Parameters(name = "Implementation: {0}")
    public static Collection<Object[]> data()
    {
        return Arrays.<Object[]>asList(
                new Object[]{"Binary Group", (PowerSetProvider) Powerset::of},
                new Object[]{"Guava PowerSet", (PowerSetProvider) Sets::powerSet}
                );
    }

    private final PowerSetProvider powerSetProvider;

    public PowersetTest(String name, PowerSetProvider powerSetProvider)
    {
        this.powerSetProvider = powerSetProvider;
    }

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

        assertThat(powerSetProvider.getPowerSet(set)).containsOnlyElementsOf(powerset);
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


        assertThat(powerSetProvider.getPowerSet(set)).containsOnlyElementsOf(powerset);
    }

    @Test
    public void shouldGeneratePowersetOfAnEmptySet() throws Exception
    {
        Set<Integer> set = newHashSet();

        //The power set of the empty set is the set which contains itself
        Set<Set<Integer>> expectedPowerSet = newHashSet();
        expectedPowerSet.add(newHashSet());

        assertThat(powerSetProvider.getPowerSet(set)).containsOnlyElementsOf(expectedPowerSet);
    }

    @Test
    public void shouldGeneratePowersetOfSetContainingTheEmptySet() throws Exception
    {
        Set<Set<Integer>> set = newHashSet();
        set.add(newHashSet());

        //The power set of the set which contains only the empty set, has two subsets, the empty set and the set which contains the empty set
        Set<Set<Set<Integer>>> powerset = newHashSet();
        powerset.add(newHashSet());

        Set<Set<Integer>> setOfEmptySet = newHashSet();
        setOfEmptySet.add(newHashSet());
        powerset.add(setOfEmptySet);

        assertThat(powerSetProvider.getPowerSet(set)).containsOnlyElementsOf(powerset);
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

    interface PowerSetProvider
    {
        <T> Set<Set<T>> getPowerSet(Set<T> set);
    }
}