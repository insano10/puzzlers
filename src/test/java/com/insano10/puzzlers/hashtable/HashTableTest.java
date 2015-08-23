package com.insano10.puzzlers.hashtable;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HashTableTest
{
    private HashTable<Key, String> table = new HashTable<>(5);

    @Test
    public void shouldBeAbleToRetrieveAValuePutIntoTheTable() throws Exception
    {
        Key key = new Key(1);
        table.put(key, "foo");

        assertThat(table.get(key)).isEqualTo("foo");
    }

    @Test
    public void shouldReturnNullForANonExistentKey() throws Exception
    {
        assertThat(table.get(new Key(5))).isNull();
    }

    @Test
    public void shouldBeAbleToStoreAndRetrieveValuesWithCollidingKeys() throws Exception
    {
        Key key = new Key(1);
        Key key2 = new Key(1);
        table.put(key, "foo");
        table.put(key2, "bar");

        assertThat(table.get(key)).isEqualTo("foo");
        assertThat(table.get(key2)).isEqualTo("bar");

    }

    @Test
    public void shouldBeAbleToPutMoreValuesThanTheInitialSizeIntoTheTable() throws Exception
    {
        //at the expense of more collisions

        Key key = new Key(1);
        Key key2 = new Key(2);
        Key key3 = new Key(3);
        Key key4 = new Key(4);
        Key key5 = new Key(5);
        Key key6 = new Key(6);
        table.put(key, "foo");
        table.put(key2, "bar");
        table.put(key3, "baz");
        table.put(key4, "woo");
        table.put(key5, "boo");
        table.put(key6, "helloo");

        assertThat(table.get(key)).isEqualTo("foo");
        assertThat(table.get(key2)).isEqualTo("bar");
        assertThat(table.get(key3)).isEqualTo("baz");
        assertThat(table.get(key4)).isEqualTo("woo");
        assertThat(table.get(key5)).isEqualTo("boo");
        assertThat(table.get(key6)).isEqualTo("helloo");
    }
}