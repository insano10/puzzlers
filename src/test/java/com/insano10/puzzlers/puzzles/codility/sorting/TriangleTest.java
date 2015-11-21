package com.insano10.puzzlers.puzzles.codility.sorting;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class TriangleTest
{

    @Test
    public void shouldReturnOneIfATriangleCanBeFormedFromTheArray() throws Exception
    {
        Triangle triangle = new Triangle();

        Assertions.assertThat(triangle.solution(new int[]{10, 2, 5, 1, 8, 20})).isEqualTo(1);
    }

    @Test
    public void shouldReturnOneIfATriangleCanBeFormedFromTheArrayWhereTheSumOverflows() throws Exception
    {
        int overflowBase = Integer.MAX_VALUE - 50;

        Triangle triangle = new Triangle();

        Assertions.assertThat(triangle.solution(new int[]{
                overflowBase + 10, overflowBase + 2, overflowBase + 5,
                overflowBase + 1, overflowBase + 8, overflowBase + 20})).isEqualTo(1);

    }

    @Test
    public void shouldReturnZeroIfATriangleCannotBeFormedFromTheArray() throws Exception
    {
        Triangle triangle = new Triangle();

        Assertions.assertThat(triangle.solution(new int[]{10, 50, 5, 1})).isEqualTo(0);

    }
}