package com.insano10.puzzlers.puzzles.codility.prefixsums;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class PassingCarsTest
{

    @Test
    public void shouldReturnTheNumberOfPairsOfCarsAccordingToTheRules() throws Exception
    {
        PassingCars passingCars = new PassingCars();

        Assertions.assertThat(passingCars.solution(new int[]{0, 1, 0, 1, 1})).isEqualTo(5);
        Assertions.assertThat(passingCars.solution(new int[]{0, 1})).isEqualTo(1);

        Assertions.assertThat(passingCars.solution(new int[]{0})).isEqualTo(0);
        Assertions.assertThat(passingCars.solution(new int[]{1})).isEqualTo(0);
        Assertions.assertThat(passingCars.solution(new int[]{1, 0})).isEqualTo(0);
        Assertions.assertThat(passingCars.solution(new int[]{1, 0, 0})).isEqualTo(0);
    }

    @Test
    public void shouldReturnMinus1WhenTheNumberOfPairsExceedsOneBillion() throws Exception
    {
        PassingCars passingCars = new PassingCars();

        int[] input = new int[2_000_000];
        input[0] = 0;

        for(int i=500_000 ; i< 2_000_000 ; i++)
        {
            input[i] = 1;
        }

        Assertions.assertThat(passingCars.solution(input)).isEqualTo(-1);
    }
}