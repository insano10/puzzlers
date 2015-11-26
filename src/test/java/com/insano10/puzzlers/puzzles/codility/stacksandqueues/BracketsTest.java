package com.insano10.puzzlers.puzzles.codility.stacksandqueues;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BracketsTest
{

    @Test
    public void shouldReturnOneIfTheStringHasProperlyNestedBrackets() throws Exception
    {
        Brackets brackets = new Brackets();

        assertThat(brackets.solution("{[()()]}")).isEqualTo(1);
        assertThat(brackets.solution("{[()]({})}")).isEqualTo(1);

        assertThat(brackets.solution("")).isEqualTo(1);

        assertThat(brackets.solution("{}")).isEqualTo(1);
        assertThat(brackets.solution("[]")).isEqualTo(1);
        assertThat(brackets.solution("()")).isEqualTo(1);
    }

    @Test
    public void shouldReturnZeroIfTheStringDoesNotHaveProperlyNestedBrackets() throws Exception
    {
        Brackets brackets = new Brackets();

        assertThat(brackets.solution("([)()]")).isEqualTo(0);
        assertThat(brackets.solution("[}")).isEqualTo(0);
        assertThat(brackets.solution("}{")).isEqualTo(0);
        assertThat(brackets.solution("(")).isEqualTo(0);
        assertThat(brackets.solution("]")).isEqualTo(0);
    }
}