package com.insano10.puzzlers.encoding;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Base64Test
{
    @Test
    public void shouldEncodeSingleCharacter() throws Exception
    {
        assertThat(Base64.encode("a")).isEqualTo("YQ==");
    }

    @Test
    public void shouldEncodeTwoCharacters() throws Exception
    {
        assertThat(Base64.encode("hi")).isEqualTo("aGk=");
    }

    @Test
    public void shouldEncodeStringWithSizeThatIsMultipleOfThree() throws Exception
    {
        assertThat(Base64.encode("ManPig")).isEqualTo("TWFuUGln");
    }

    @Test
    public void shouldEncodeAsciiString() throws Exception
    {
        assertThat(Base64.encode("secret message")).isEqualTo("c2VjcmV0IG1lc3NhZ2U=");
    }

    @Test
    public void shouldEncodeUtf8String() throws Exception
    {
        assertThat(Base64.encode("©£Æ")).isEqualTo("wqnCo8OG");
    }

    @Test
    public void shouldEncodeEmptyString() throws Exception
    {
        assertThat(Base64.encode("")).isEmpty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotEncodeNullString() throws Exception
    {
        Base64.encode(null);
    }
}