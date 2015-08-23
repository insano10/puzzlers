package com.insano10.puzzlers.hashtable;

import com.insano10.puzzlers.lists.LinkedList;

import java.lang.reflect.Array;

public class HashTable<K, V>
{
    private final TableEntry[] table;
    private int size;

    public HashTable(int initialSize)
    {
        this.table = (TableEntry[])Array.newInstance(TableEntry.class, initialSize);
        this.size = initialSize;
    }

    public void put(K key, V value)
    {
        int index = key.hashCode() % size;

        TableEntry existing = table[index];

        if(existing == null)
        {
            existing = new TableEntry();
            table[index] = existing;
        }
        existing.add(key, value);
    }

    public V get(K key)
    {
        int index = key.hashCode() % size;

        TableEntry existing = table[index];

        if(existing != null)
        {
            return existing.get(key);
        }

        return null;
    }

    private class TableEntry
    {
        LinkedList<BucketEntry<K,V>> values = new LinkedList<>();

        public void add(K key, V value)
        {
            values.add(new BucketEntry<>(key, value));
        }

        public V get(K key)
        {
            for (int i = 0; i < values.size(); i++)
            {
                if(values.get(i).key.equals(key))
                {
                    return values.get(i).value;
                }
            }
            return null;
        }
    }

    private static class BucketEntry<K,V>
    {
        private K key;
        private V value;

        private BucketEntry(K key, V value)
        {
            this.key = key;
            this.value = value;
        }
    }
}
