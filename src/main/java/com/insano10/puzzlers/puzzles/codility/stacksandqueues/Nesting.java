package com.insano10.puzzlers.puzzles.codility.stacksandqueues;

import java.util.Stack;

public class Nesting
{
    public int solution(String input)
    {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++)
        {
            char c = input.charAt(i);
            if (c == '(')
            {
                stack.push(c);
            }
            else if (c == ')' && stack.size() > 0)
            {
                stack.pop();
            }
            else
            {
                return 0;
            }
        }
        return stack.isEmpty() ? 1 : 0;
    }
}
