package com.insano10.puzzlers.strings;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringHasAllUniqueCharactersTest {


    @Test
    public void shouldReturnTrueWhenStringHasAllUniqueCharacters() throws Exception {

        assertThat(StringHasAllUniqueCharacters.hasAllUniqueChars("abcdefg")).isTrue();
        assertThat(StringHasAllUniqueCharacters.hasAllUniqueChars("a")).isTrue();

        assertThat(StringHasAllUniqueCharacters.hasAllUniqueCharsNoAdditionalDataStructure("abcdefg")).isTrue();
        assertThat(StringHasAllUniqueCharacters.hasAllUniqueCharsNoAdditionalDataStructure("a")).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenStringHasAllUniqueCharacters() throws Exception {

        assertThat(StringHasAllUniqueCharacters.hasAllUniqueChars("aaaaa")).isFalse();
        assertThat(StringHasAllUniqueCharacters.hasAllUniqueChars("abcda")).isFalse();
        assertThat(StringHasAllUniqueCharacters.hasAllUniqueChars("ababa")).isFalse();

        assertThat(StringHasAllUniqueCharacters.hasAllUniqueCharsNoAdditionalDataStructure("aaaaa")).isFalse();
        assertThat(StringHasAllUniqueCharacters.hasAllUniqueCharsNoAdditionalDataStructure("abcda")).isFalse();
        assertThat(StringHasAllUniqueCharacters.hasAllUniqueCharsNoAdditionalDataStructure("ababa")).isFalse();
    }

    @Test
    public void shouldReturnTrueWhenStringIsEmpty() throws Exception {

        assertThat(StringHasAllUniqueCharacters.hasAllUniqueChars("")).isTrue();

        assertThat(StringHasAllUniqueCharacters.hasAllUniqueCharsNoAdditionalDataStructure("")).isTrue();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenStringIsNull() throws Exception {

        StringHasAllUniqueCharacters.hasAllUniqueChars(null);

        StringHasAllUniqueCharacters.hasAllUniqueCharsNoAdditionalDataStructure(null);
    }
}