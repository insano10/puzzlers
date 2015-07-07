package com.insano10.puzzlers.lists;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

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

        assertThat(list.get(0)).isEqualTo("hello");
        assertThat(list.get(1)).isEqualTo("world");
        assertThat(list.get(2)).isEqualTo("this");
        assertThat(list.get(3)).isEqualTo("is");
        assertThat(list.get(4)).isEqualTo("a");
        assertThat(list.get(5)).isEqualTo("list");
    }

    @Test
    public void shouldRemoveElementsFromList() throws Exception
    {
        LinkedList<String> list = new LinkedList<>();

        list.add("hello");
        list.add("world");

        assertThat(list.remove("notThere")).isFalse();

        assertThat(list.remove("world")).isTrue();
        assertThat(list.get(0)).isEqualTo("hello");
        assertThat(list.size()).isEqualTo(1);

        assertThat(list.remove("hello")).isTrue();
        assertThat(list.size()).isEqualTo(0);

        assertThat(list.remove("stillNotThere")).isFalse();
    }

    @Test
    public void shouldRemoveHeadOfTheList() throws Exception
    {
        LinkedList<String> list = new LinkedList<>();

        list.add("hello");
        list.add("world");

        assertThat(list.remove("hello")).isTrue();
        assertThat(list.get(0)).isEqualTo("world");
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    public void shouldRemoveThenAddElementToList() throws Exception
    {
        LinkedList<String> list = new LinkedList<>();

        list.add("hello");

        assertThat(list.remove("hello")).isTrue();
        assertThat(list.size()).isEqualTo(0);

        list.add("world");
        assertThat(list.get(0)).isEqualTo("world");
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    public void shouldRemoveDuplicateElements() throws Exception
    {
        LinkedList<String> list = new LinkedList<>();

        list.add("hello");
        list.add("hello");
        list.add("hello");

        assertThat(list.size()).isEqualTo(3);

        assertThat(list.removeDuplicates()).isTrue();

        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    public void shouldRemoveDuplicateElementsNoExtraDataStructure() throws Exception
    {
        LinkedList<String> list = new LinkedList<>();

        list.add("hello");
        list.add("hello");
        list.add("hello");

        assertThat(list.size()).isEqualTo(3);

        assertThat(list.removeDuplicatesNoExtraDataStructure()).isTrue();

        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    public void shouldRemoveMultipleDuplicateElements() throws Exception
    {
        LinkedList<String> list = new LinkedList<>();

        list.add("hello");
        list.add("world");
        list.add("world");
        list.add("hello");
        list.add("world");

        assertThat(list.size()).isEqualTo(5);

        assertThat(list.removeDuplicates()).isTrue();

        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    public void shouldRemoveMultipleDuplicateElementsNoExtraDataStructure() throws Exception
    {
        LinkedList<String> list = new LinkedList<>();

        list.add("hello");
        list.add("world");
        list.add("world");
        list.add("hello");
        list.add("world");

        assertThat(list.size()).isEqualTo(5);

        assertThat(list.removeDuplicatesNoExtraDataStructure()).isTrue();

        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    public void shouldNotRemoveNonDuplicateElements() throws Exception
    {
        LinkedList<String> list = new LinkedList<>();

        list.add("hello");
        list.add("world");
        list.add("!");

        assertThat(list.size()).isEqualTo(3);

        assertThat(list.removeDuplicates()).isFalse();

        assertThat(list.size()).isEqualTo(3);
    }

    @Test
    public void shouldNotRemoveNonDuplicateElementsNoExtraDataStructure() throws Exception
    {
        LinkedList<String> list = new LinkedList<>();

        list.add("hello");
        list.add("world");
        list.add("!");

        assertThat(list.size()).isEqualTo(3);

        assertThat(list.removeDuplicatesNoExtraDataStructure()).isFalse();

        assertThat(list.size()).isEqualTo(3);
    }

    @Test
    public void shouldNotRemoveNonDuplicateElementsFromEmptyList() throws Exception
    {
        LinkedList<String> list = new LinkedList<>();

        assertThat(list.size()).isEqualTo(0);
        assertThat(list.removeDuplicates()).isFalse();
        assertThat(list.size()).isEqualTo(0);
    }

    @Test
    public void shouldNotRemoveNonDuplicateElementsFromEmptyListNoExtraDataStructure() throws Exception
    {
        LinkedList<String> list = new LinkedList<>();

        assertThat(list.size()).isEqualTo(0);
        assertThat(list.removeDuplicatesNoExtraDataStructure()).isFalse();
        assertThat(list.size()).isEqualTo(0);
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