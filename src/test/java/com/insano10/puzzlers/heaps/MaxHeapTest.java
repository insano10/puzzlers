package com.insano10.puzzlers.heaps;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Comparator;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class MaxHeapTest
{
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private static final Comparator<Integer> MAX_COMPARATOR = (o1, o2) -> o2.compareTo(o1);

    /*
              23
          21      23
        17  2   9   20
      1

        = [23,21,23,17,2,9,20,1]

     */
    @Ignore
    @Test
    public void shouldExtractElementsInMaxOrderFromHeapThatWasCreatedWithoutTheNeedToReorderElementsOnInsertion() throws Exception
    {
        Heap<Integer> heap = new Heap<>(10, MAX_COMPARATOR);

        heap.add(23);
        heap.add(21);
        heap.add(23);
        heap.add(17);
        heap.add(2);
        heap.add(9);
        heap.add(20);
        heap.add(1);

        extractAndVerify(heap, 23);
        extractAndVerify(heap, 23);
        extractAndVerify(heap, 21);
        extractAndVerify(heap, 20);
        extractAndVerify(heap, 17);
        extractAndVerify(heap, 9);
        extractAndVerify(heap, 2);
        extractAndVerify(heap, 1);
    }

    @Ignore
    @Test
    public void shouldExtractElementsInMaxOrderFromHeapThatWasCreatedByReOrdingInsertedElements() throws Exception
    {
        Heap<Integer> heap = new Heap<>(10, MAX_COMPARATOR);

        heap.add(1);
        heap.add(20);
        heap.add(9);
        heap.add(2);
        heap.add(17);
        heap.add(23);
        heap.add(21);
        heap.add(23);

        extractAndVerify(heap, 23);
        extractAndVerify(heap, 23);
        extractAndVerify(heap, 21);
        extractAndVerify(heap, 20);
        extractAndVerify(heap, 17);
        extractAndVerify(heap, 9);
        extractAndVerify(heap, 2);
        extractAndVerify(heap, 1);
    }

    @Test
    public void shouldSeeTheMaximumElementWhenPeekingTheRoot() throws Exception
    {
        Heap<Integer> heap = new Heap<>(10, MAX_COMPARATOR);

        heap.add(6);
        heap.add(1);
        heap.add(9);
        heap.add(3);

        Optional<Integer> element = heap.peekRoot();

        assertThat(element.get()).isEqualTo(9);
    }

    @Test
    public void shouldMaintainOrderingAfterExtractingAndReInserting() throws Exception
    {
        Heap<Integer> heap = new Heap<>(10, MAX_COMPARATOR);

        heap.add(6);
        heap.add(1);
        heap.add(3);
        heap.add(9);

        extractAndVerify(heap, 9);
        extractAndVerify(heap, 6);

        heap.add(2);
        heap.add(7);

        extractAndVerify(heap, 7);
        extractAndVerify(heap, 3);
        extractAndVerify(heap, 2);
        extractAndVerify(heap, 1);
    }

    private void extractAndVerify(Heap<Integer> heap, final Integer expected)
    {
        Optional<Integer> element = heap.extract();
        assertThat(element.get()).isEqualTo(expected);
    }
}