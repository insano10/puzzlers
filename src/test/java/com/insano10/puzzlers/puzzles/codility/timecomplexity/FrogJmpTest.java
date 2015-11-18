package com.insano10.puzzlers.puzzles.codility.timecomplexity;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class FrogJmpTest
{

    @Test
    public void shouldFindNumJumpsToGetFromXtoY() throws Exception
    {
        Assertions.assertThat(FrogJmp.solution(10, 85, 30)).isEqualTo(3);
    }

    @Test
    public void shouldFindNumJumpsToGetFromXtoY2() throws Exception
    {
        Assertions.assertThat(FrogJmp.solution(1, 5, 2)).isEqualTo(2);
    }
}