package com.insano10.puzzlers.puzzles.codility.countingElements;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class FrogRiverOneTest
{
    @Test
    public void shouldFindTheFirstIndexAtWhichAllRequiredValuesHaveBeenSeen() throws Exception
    {
        int[] input = new int[]{1, 3, 1, 4, 2, 3, 5, 4};

        FrogRiverOne frogRiverOne = new FrogRiverOne();

        Assertions.assertThat(frogRiverOne.solution(5, input)).isEqualTo(6);
    }

    @Test
    public void shouldReturnMinusOneWhenTheRequiredValuesCannotBeFound() throws Exception
    {
        int[] input = new int[]{1, 3, 1, 4, 2, 3, 5, 4};

        FrogRiverOne frogRiverOne = new FrogRiverOne();

        Assertions.assertThat(frogRiverOne.solution(6, input)).isEqualTo(-1);
    }
}