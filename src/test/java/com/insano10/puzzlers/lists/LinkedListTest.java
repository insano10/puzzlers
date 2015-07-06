package com.insano10.puzzlers.lists;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class LinkedListTest
{

    @Test
    public void shouldRetrieveElementFromList() throws Exception
    {
        LinkedList<String> list = new LinkedList<>();

        list.add("hello");
        list.add("world");
        list.add("this");
        list.add("is");
        list.add("a");
        list.add("list");

        Assertions.assertThat(list.get(0)).isEqualTo("hello");
        Assertions.assertThat(list.get(1)).isEqualTo("world");
        Assertions.assertThat(list.get(2)).isEqualTo("this");
        Assertions.assertThat(list.get(3)).isEqualTo("is");
        Assertions.assertThat(list.get(4)).isEqualTo("a");
        Assertions.assertThat(list.get(5)).isEqualTo("list");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowIndexOutOfBoundsExceptionWhenAskingForTheZerothElementInAnEmptyList() throws Exception
    {
        LinkedList<String> list = new LinkedList<>();

        list.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowIndexOutOfBoundsExceptionWhenAskingForAnElementAtAnIndexThatDoesNotExist() throws Exception
    {
        LinkedList<String> list = new LinkedList<>();

        list.get(3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenAskingForANegativeIndex() throws Exception
    {
        LinkedList<String> list = new LinkedList<>();
        list.add("hello");
        list.add("world");
        list.add("!");

        list.get(-2);
    }
}