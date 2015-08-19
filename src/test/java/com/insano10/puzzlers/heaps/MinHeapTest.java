package com.insano10.puzzlers.heaps;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Comparator;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class MinHeapTest
{
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private static final Comparator<Integer> MIN_COMPARATOR = Integer::compareTo;

    @Test
    public void shouldExtractElementFromNonEmptyHeap() throws Exception
    {
        Heap<Integer> heap = new Heap<>(10, MIN_COMPARATOR);
        heap.add(1);

        extractAndVerify(heap, 1);
    }

    @Test
    public void shouldGetEmptyOptionalWhenExtractingFromEmptyHeap() throws Exception
    {
        Heap<Integer> heap = new Heap<>(10, MIN_COMPARATOR);

        Optional<Integer> element = heap.extract();

        assertThat(element.isPresent()).isFalse();
    }


    /*
              1
          6      3
        9  10  6   4

        = [1,6,3,9,10,6,4]

     */
    @Test
    public void shouldExtractElementsInMinOrderFromHeapThatWasCreatedWithoutTheNeedToReorderElementsOnInsertion() throws Exception
    {
        Heap<Integer> heap = new Heap<>(10, MIN_COMPARATOR);

        heap.add(1);
        heap.add(6);
        heap.add(3);
        heap.add(9);
        heap.add(10);
        heap.add(6);
        heap.add(4);

        extractAndVerify(heap, 1);
        extractAndVerify(heap, 3);
        extractAndVerify(heap, 4);
        extractAndVerify(heap, 6);
        extractAndVerify(heap, 6);
        extractAndVerify(heap, 9);
        extractAndVerify(heap, 10);
    }

    @Test
    public void shouldExtractElementsInMinOrderFromHeapThatWasCreatedByReOrdingInsertedElements() throws Exception
    {
        Heap<Integer> heap = new Heap<>(10, MIN_COMPARATOR);

        heap.add(10);
        heap.add(9);
        heap.add(6);
        heap.add(6);
        heap.add(4);
        heap.add(3);
        heap.add(1);

        extractAndVerify(heap, 1);
        extractAndVerify(heap, 3);
        extractAndVerify(heap, 4);
        extractAndVerify(heap, 6);
        extractAndVerify(heap, 6);
        extractAndVerify(heap, 9);
        extractAndVerify(heap, 10);
    }

    @Test
    public void shouldSeeTheMinimumElementWhenPeekingTheRoot() throws Exception
    {
        Heap<Integer> heap = new Heap<>(10, MIN_COMPARATOR);

        heap.add(6);
        heap.add(1);
        heap.add(3);
        heap.add(9);

        Optional<Integer> element = heap.peekRoot();

        assertThat(element.get()).isEqualTo(1);
    }

    @Test
    public void shouldMaintainOrderingAfterExtractingAndReInserting() throws Exception
    {
        Heap<Integer> heap = new Heap<>(10, MIN_COMPARATOR);

        heap.add(6);
        heap.add(1);
        heap.add(3);
        heap.add(9);

        extractAndVerify(heap, 1);
        extractAndVerify(heap, 3);

        heap.add(2);
        heap.add(7);

        extractAndVerify(heap, 2);
        extractAndVerify(heap, 6);
        extractAndVerify(heap, 7);
        extractAndVerify(heap, 9);
    }

    @Test
    public void shouldBeAbleToInsertMoreElementsThanTheInitialSizeOfTheHeap() throws Exception
    {
        Heap<Integer> heap = new Heap<>(4, MIN_COMPARATOR);

        heap.add(6);
        heap.add(1);
        heap.add(3);
        heap.add(9);
        heap.add(9);
        heap.add(2);

        extractAndVerify(heap, 1);
        extractAndVerify(heap, 2);
        extractAndVerify(heap, 3);
        extractAndVerify(heap, 6);
        extractAndVerify(heap, 9);
        extractAndVerify(heap, 9);
    }

    @Test
    public void shouldNotAllowHeapToGrowBeyondMaxSize() throws Exception
    {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Heap is full [5]. Cannot add more elements");

        Heap<Integer> heap = new Heap<>(2, MIN_COMPARATOR, 5);

        heap.add(6);
        heap.add(1);
        heap.add(3);
        heap.add(9);
        heap.add(9);
        heap.add(2);

    }

    private void extractAndVerify(Heap<Integer> heap, final Integer expected)
    {
        Optional<Integer> element = heap.extract();
        assertThat(element.get()).isEqualTo(expected);
    }
}