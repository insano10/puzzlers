package com.insano10.puzzlers.puzzles.codility.prefixsums;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class CountDivTest
{
    @Test
    public void shouldReturnACountOfTheNumbersBetweenAAndBDivisbleByK() throws Exception
    {
        CountDiv countDiv = new CountDiv();

        Assertions.assertThat(countDiv.solution(6, 11, 2)).isEqualTo(3); //6, 8, 10
        Assertions.assertThat(countDiv.solution(6, 12, 3)).isEqualTo(3); //6, 9, 12
        Assertions.assertThat(countDiv.solution(1, 10, 1)).isEqualTo(10); //1,2,3,4,5,6,7,8,9,10
        Assertions.assertThat(countDiv.solution(0, 0, 1)).isEqualTo(1); //zero is divisible by any integer
        Assertions.assertThat(countDiv.solution(1, 2, 3)).isEqualTo(0);
        Assertions.assertThat(countDiv.solution(0, 0, 11)).isEqualTo(1);
        Assertions.assertThat(countDiv.solution(1, 1, 11)).isEqualTo(0);
        Assertions.assertThat(countDiv.solution(0, Integer.MAX_VALUE, 1)).isEqualTo(Integer.MAX_VALUE);
        Assertions.assertThat(countDiv.solution(0, Integer.MAX_VALUE, Integer.MAX_VALUE)).isEqualTo(2);
    }
}