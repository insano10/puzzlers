package com.insano10.puzzlers.puzzles.codility.stacksandqueues;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NestingTest
{
    @Test
    public void shouldReturnOneIfStringIsProperlyNested() throws Exception
    {
        Nesting nesting = new Nesting();
        assertThat(nesting.solution("")).isEqualTo(1);
        assertThat(nesting.solution("()")).isEqualTo(1);
        assertThat(nesting.solution("(()())")).isEqualTo(1);
        assertThat(nesting.solution("()()")).isEqualTo(1);
        assertThat(nesting.solution("(((()()(()))))")).isEqualTo(1);
    }

    @Test
    public void shouldReturnZeroIfStringIsNotProperlyNested() throws Exception
    {
        Nesting nesting = new Nesting();
        assertThat(nesting.solution("(")).isEqualTo(0);
        assertThat(nesting.solution(")")).isEqualTo(0);
        assertThat(nesting.solution("(()")).isEqualTo(0);
        assertThat(nesting.solution(")(")).isEqualTo(0);
        assertThat(nesting.solution("()())(")).isEqualTo(0);
    }
}