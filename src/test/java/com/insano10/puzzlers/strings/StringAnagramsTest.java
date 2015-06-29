package com.insano10.puzzlers.strings;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringAnagramsTest {


    @Test
    public void shouldReturnTrueWhenStringsAreAnagrams() throws Exception {

        assertThat(StringAnagrams.areAnagrams("abcdef", "fadebc")).isTrue();
        assertThat(StringAnagrams.areAnagrams("aaaaaaa", "aaaaaaa")).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenGivenEmptyStrings() throws Exception {

        assertThat(StringAnagrams.areAnagrams("", "")).isTrue();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenGivenANullString() throws Exception {

        StringAnagrams.areAnagrams("", null);
    }

    @Test
    public void shouldReturnFalseWhenStringsAreNotAnagrams() throws Exception {

        assertThat(StringAnagrams.areAnagrams("someString", "thisIsNotAnAnagram")).isFalse();
    }

    @Test
    public void shouldBeCaseSensitiveWhenComparingString() throws Exception {

        assertThat(StringAnagrams.areAnagrams("someString", "somestring")).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenStringIsASubStringButNotAnAnagram() throws Exception {

        assertThat(StringAnagrams.areAnagrams("someString", "String")).isFalse();
    }
}