package com.insano10.puzzlers.puzzles.codility;

import com.insano10.puzzlers.puzzles.codility.timecomplexity.PermMissingElement;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PermMissingElementTest
{

    @Test
    public void shouldFindTheMissingElement() throws Exception
    {
        int[] input = new int[] {2, 3, 1, 5};

        PermMissingElement permMissingElement = new PermMissingElement();

        assertThat(permMissingElement.solution(input)).isEqualTo(4);
    }
}