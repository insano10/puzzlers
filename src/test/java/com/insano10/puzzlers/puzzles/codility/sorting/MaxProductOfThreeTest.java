package com.insano10.puzzlers.puzzles.codility.sorting;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MaxProductOfThreeTest
{

    @Test
    public void shouldReturnTheMaxProductOfThreeNumbers() throws Exception
    {
        MaxProductOfThree maxProductOfThree = new MaxProductOfThree();

        Assertions.assertThat(maxProductOfThree.solution(new int[] {-3, 1, 2, -2, 5, 6})).isEqualTo(60);
    }

    @Test
    public void shouldReturnTheMaxProductOfThreeNumbersWhenTheMaximalTripletContainsNegativeNumbers() throws Exception
    {
        MaxProductOfThree maxProductOfThree = new MaxProductOfThree();

        Assertions.assertThat(maxProductOfThree.solution(new int[] {-3, 1, 2, -2, -5, -6})).isEqualTo(60);
        Assertions.assertThat(maxProductOfThree.solution(new int[] {-3, -1, -2, -2, -5, -6})).isEqualTo(-4);
        Assertions.assertThat(maxProductOfThree.solution(new int[] {-1, -2, 3, 4})).isEqualTo(8);
    }

    @Test
    public void shouldReturnTheMaxProductOfThreeNumbersWhenThereAreOnlyThree() throws Exception
    {
        MaxProductOfThree maxProductOfThree = new MaxProductOfThree();

        Assertions.assertThat(maxProductOfThree.solution(new int[] {1, 2, 3})).isEqualTo(6);
        Assertions.assertThat(maxProductOfThree.solution(new int[] {-1, -2, -3})).isEqualTo(-6);
        Assertions.assertThat(maxProductOfThree.solution(new int[] {-1, -2, 3})).isEqualTo(6);
    }
}