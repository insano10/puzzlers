package com.insano10.puzzlers.hashtable;

public class Key
{
    private int hashcode;

    public Key(int hashcode)
    {
        this.hashcode = hashcode;
    }

    @Override
    public int hashCode()
    {
        return hashcode;
    }
}
