package com.insano10.puzzlers.strings;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringHasAllUniqueCharactersTest {


    @Test
    public void shouldReturnTrueWhenStringHasAllUniqueCharacters() throws Exception {

        assertThat(StringHasAllUniqueCharacters.hasAllUniqueChars("abcdefg")).isTrue();
        assertThat(StringHasAllUniqueCharacters.hasAllUniqueChars("a")).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenStringHasAllUniqueCharacters() throws Exception {

        assertThat(StringHasAllUniqueCharacters.hasAllUniqueChars("aaaaa")).isFalse();
        assertThat(StringHasAllUniqueCharacters.hasAllUniqueChars("abcda")).isFalse();
        assertThat(StringHasAllUniqueCharacters.hasAllUniqueChars("ababa")).isFalse();
    }

    @Test
    public void shouldReturnTrueWhenStringIsEmpty() throws Exception {

        assertThat(StringHasAllUniqueCharacters.hasAllUniqueChars("")).isTrue();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenStringIsNull() throws Exception {

        StringHasAllUniqueCharacters.hasAllUniqueChars(null);
    }
}