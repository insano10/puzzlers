package com.insano10.puzzlers.puzzles.codility.stacksandqueues;

import java.util.*;

public class Brackets
{
    private final List<Character> OPENING = new ArrayList<>();
    private final List<Character> CLOSING = new ArrayList<>();

    public Brackets()
    {
        OPENING.add('(');
        OPENING.add('{');
        OPENING.add('[');

        CLOSING.add(')');
        CLOSING.add('}');
        CLOSING.add(']');
    }

    public int solution(String input)
    {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++)
        {
            char currentChar = input.charAt(i);

            if (OPENING.contains(currentChar))
            {
                stack.push(currentChar);
            }
            else if(CLOSING.contains(currentChar) && !stack.isEmpty())
            {
                //only pop if the bracket closes a matching opening bracket of the same type
                if(CLOSING.indexOf(currentChar) == OPENING.indexOf(stack.peek()))
                {
                    stack.pop();
                }
            }
            else
            {
                return 0;
            }
        }

        return stack.isEmpty() ? 1 : 0;
    }
}
