package com.insano10.puzzlers.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

            if (!next.hasChildWithValue(c))
            {
                next.addChild(MultiNode.of(c));
            }
            next = next.getChild(c);

            boolean isTerminalChar = (i == chars.length - 1);
            if (isTerminalChar)
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

            if (!next.hasChildWithValue(c))
            {
                return false;
            }

            next = next.getChild(c);
        }

        return next.isTerminal();
    }

    private void validateStringInput(String string)
    {
        if (string == null)
        {
            throw new IllegalArgumentException("Input cannot be null");
        }
    }

    public List<String> splitIntoValidSubStrings(String string)
    {
        List<String> words = new ArrayList<>();
        StringBuilder currentWord = new StringBuilder();
        char[] chars = string.toCharArray();

        MultiNode<Character> next = root;
        int nextCharIdx = 0;

        while (nextCharIdx < chars.length)
        {
            char c = chars[nextCharIdx];

            if (next.hasChildWithValue(c))
            {
                //keep moving down the tree
                next = next.getChild(c);
                currentWord.append(next.data);
                nextCharIdx++;
            }
            else
            {
                if (next.equals(root))
                {
                    //we were already on the root but failed to find a starting point. skip over this char
                    nextCharIdx++;
                }
                else
                {
                    //we can go no further, bank the word and start again from the top
                    if (next.isTerminal())
                    {
                        words.add(currentWord.toString());
                        currentWord.setLength(0);
                        next = root;
                    }
                }
            }
        }

        //add the final word in the buffer
        if (next.isTerminal())
        {
            words.add(currentWord.toString());
        }

        return words;
    }

    public List<String> getSortedKeys()
    {
        /*

        1. push root onto stack
        2a. if this next is a child of the previously visited node, pop and delete last char from builder
        2b. else append char to builder and add builder word to sorted list if node is terminal
        3a. if node has no children, pop and delete last char from builder
        3b. else push children in reverse order onto the stack
        4. repeat until stack is empty
         */

        List<String> sortedKeys = new ArrayList<>();
        StringBuilder currentWord = new StringBuilder();

        Stack<MultiNode<Character>> working = new Stack<>();
        working.push(root);

        MultiNode<Character> previous = null;
        while (!working.isEmpty())
        {
            MultiNode<Character> next = working.peek();

            if (previous != null && next.children.contains(previous))
            {
                //going back up (must have processed all children to get back to the parent node)
                working.pop();
                if (next.data != null)
                {
                    currentWord.deleteCharAt(currentWord.length() - 1);
                }
            }
            else
            {
                //process this node
                if (next.data != null)
                {
                    currentWord.append(next.data);

                    if (next.isTerminal())
                    {
                        sortedKeys.add(currentWord.toString());
                    }
                }

                if (next.children.isEmpty())
                {
                    //reached a leaf
                    working.pop();
                    currentWord.deleteCharAt(currentWord.length() - 1);
                }
                else
                {
                    //add children to the stack
                    for (MultiNode<Character> child : next.getChildrenInDescendingOrder())
                    {
                        working.push(child);
                    }
                }
            }

            previous = next;
        }

        return sortedKeys;
    }
}
