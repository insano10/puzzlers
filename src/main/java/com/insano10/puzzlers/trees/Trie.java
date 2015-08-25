package com.insano10.puzzlers.trees;

import java.util.List;

public class Trie
{
    MultiNode<Character> root = MultiNode.of(null);

    public void insert(String string)
    {
        validateStringInput(string);

        char[] chars = string.toCharArray();
        MultiNode<Character> next = root;

        for (int i = 0; i < chars.length; i++)
        {
            char c = chars[i];

            if(!next.hasChild(c))
            {
                next.addChild(MultiNode.of(c));
            }
            next = next.getChild(c);

            boolean isTerminalChar = (i == chars.length-1);
            if(isTerminalChar)
            {
                next.terminal();
            }

        }
    }

    public boolean isValidString(String string)
    {
        validateStringInput(string);

        char[] chars = string.toCharArray();
        MultiNode<Character> next = root;

        for (int i = 0; i < chars.length; i++)
        {
            char c = chars[i];

            if (!next.hasChild(c))
            {
                return false;
            }

            next = next.getChild(c);
        }

        return next.isTerminal();
    }

    private void validateStringInput(String string)
    {
        if(string == null)
        {
            throw new IllegalArgumentException("Input cannot be null");
        }
    }

    public List<String> splitIntoValidSubStrings(String string)
    {
        return null;
    }

    public List<String> getSortedKeys()
    {
        return null;
    }
}
