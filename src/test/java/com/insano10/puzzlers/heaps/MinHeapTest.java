package com.insano10.puzzlers.heaps;

import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MinHeapTest
{
    @Test
    public void shouldExtractElementFromNonEmptyHeap() throws Exception
    {
        MinHeap<Integer> heap = new MinHeap<>(10);
        heap.add(1);

        extractAndVerify(heap, 1);
    }

    @Test
    public void shouldGetNullWhenExtractingFromEmptyHeap() throws Exception
    {
        MinHeap<Integer> heap = new MinHeap<>(10);

        Integer element = heap.extract();

        assertThat(element).isNull();
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
        MinHeap<Integer> heap = new MinHeap<>(10);

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

    /*
            1
        6      3
      9  10  6   4

      = [1,6,3,9,10,6,4]

   */
    @Test
    public void shouldExtractElementsInMinOrderFromHeapThatWasCreatedByReOrdingInsertedElements() throws Exception
    {
        MinHeap<Integer> heap = new MinHeap<>(10);

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

    @Ignore
    @Test
    public void shouldSeeTheMinimumElementWhenPeekingTheRoot() throws Exception
    {
        MinHeap<Integer> heap = new MinHeap<>(10);

        heap.add(6);
        heap.add(1);
        heap.add(3);
        heap.add(9);

        Integer element = heap.peekRoot();

        assertThat(element).isEqualTo(1);
    }

    @Ignore
    @Test
    public void shouldMaintainOrderingAfterExtractingAndReInserting() throws Exception
    {
        MinHeap<Integer> heap = new MinHeap<>(10);

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

    @Ignore
    @Test
    public void shouldBeAbleToInsertMoreElementsThanTheInitialSizeOfTheHeap() throws Exception
    {
        MinHeap<Integer> heap = new MinHeap<>(4);

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

    //todo: max size of heap? shouldn't let it grow unbounded

    private void extractAndVerify(MinHeap<Integer> heap, final Integer expected)
    {
        int element = heap.extract();
        assertThat(element).isEqualTo(expected);
    }
}