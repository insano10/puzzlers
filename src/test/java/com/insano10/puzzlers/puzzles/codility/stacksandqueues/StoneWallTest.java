package com.insano10.puzzlers.puzzles.codility.stacksandqueues;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StoneWallTest
{

    @Test
    public void shouldReturnMinimumNumberOfBlocksRequiredToMakeWallSpecification() throws Exception
    {
        StoneWall stoneWall = new StoneWall();

        assertThat(stoneWall.solution(new int[]{8, 8, 5, 7, 9, 8, 7, 4, 8})).isEqualTo(7);

        assertThat(stoneWall.solution(new int[]{1, 2, 3, 4})).isEqualTo(4);
        assertThat(stoneWall.solution(new int[]{4, 3, 2, 1})).isEqualTo(4);

        assertThat(stoneWall.solution(new int[]{1})).isEqualTo(1);
        assertThat(stoneWall.solution(new int[]{1,1,1,1,1,1})).isEqualTo(1);

        assertThat(stoneWall.solution(new int[]{1,2,1,2,1})).isEqualTo(3);
    }
}