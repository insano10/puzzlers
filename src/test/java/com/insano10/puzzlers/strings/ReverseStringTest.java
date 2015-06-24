package com.insano10.puzzlers.strings;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReverseStringTest {

    @Test
    public void shouldReverseOddLengthString() {

        assertThat(ReverseString.reverse("abcde")).isEqualTo("edcba");
    }

    @Test
    public void shouldReverseEvenLengthString() {

        assertThat(ReverseString.reverse("abcd")).isEqualTo("dcba");
    }

    @Test
    public void shouldReverseOddLengthCStyleString() {

        String string = new String(new char[]{'a','b','c','d','e','\0'});
        String expectedString = new String(new char[]{'e','d','c','b','a','\0'});
        assertThat(ReverseString.reverse(string)).isEqualTo(expectedString);
    }

    @Test
    public void shouldReverseEvenLengthCStyleString() {

        String string = new String(new char[]{'a','b','c','d','\0'});
        String expectedString = new String(new char[]{'d','c','b','a','\0'});
        assertThat(ReverseString.reverse(string)).isEqualTo(expectedString);
    }

    @Test
    public void shouldReverseEmptyString() {

        assertThat(ReverseString.reverse("")).isEqualTo("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenPassedNull() {

        ReverseString.reverse(null);
    }
}