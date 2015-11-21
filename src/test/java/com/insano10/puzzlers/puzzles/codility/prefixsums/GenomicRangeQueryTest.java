package com.insano10.puzzlers.puzzles.codility.prefixsums;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class GenomicRangeQueryTest
{
    //Impact factors:
    //A = 1
    //C = 2
    //G = 3
    //T = 4

    @Test
    public void shouldReturnTheMinimalImpactFactorBetweenAllRanges() throws Exception
    {
        GenomicRangeQuery genomicRangeQuery = new GenomicRangeQuery();

        int[] solution = genomicRangeQuery.solution(
                "CAGCCTA",
                new int[]{2, 5, 0},
                new int[]{4, 5, 6});

        Assertions.assertThat(solution).isEqualTo(new int[]{2, 4, 1});
    }

    @Test
    public void shouldReturnTheMinimalImpactFactorWhenRangeIsSingleElement() throws Exception
    {
        GenomicRangeQuery genomicRangeQuery = new GenomicRangeQuery();

        int[] solution = genomicRangeQuery.solution(
                "CAGCCTA",
                new int[]{0, 1, 2, 3, 4, 5, 6},
                new int[]{0, 1, 2, 3, 4, 5, 6});

        Assertions.assertThat(solution).isEqualTo(new int[]{2, 1, 3, 2, 2, 4, 1});
    }

    @Test
    public void shouldReturnTheMinimalImpactFactorWhenNotAllNucleotidesArePresent() throws Exception
    {
        GenomicRangeQuery genomicRangeQuery = new GenomicRangeQuery();

        int[] solution = genomicRangeQuery.solution(
                "TGCTG",
                new int[]{0},
                new int[]{1});

        Assertions.assertThat(solution).isEqualTo(new int[]{3});
    }
}