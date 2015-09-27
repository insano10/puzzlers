package com.insano10.puzzlers.encoding;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Base64Test
{
    @Test
    public void shouldEncodeSingleCharacter() throws Exception
    {
        assertThat(Base64Encoder.encode("a")).isEqualTo("YQ==");
        assertThat(Base64Decoder.decode("YQ==")).isEqualTo("a");
    }

    @Test
    public void shouldEncodeTwoCharacters() throws Exception
    {
        assertThat(Base64Encoder.encode("hi")).isEqualTo("aGk=");
        assertThat(Base64Decoder.decode("aGk=")).isEqualTo("hi");
    }

    @Test
    public void shouldEncodeStringWithSizeThatIsMultipleOfThree() throws Exception
    {
        assertThat(Base64Encoder.encode("ManPig")).isEqualTo("TWFuUGln");
        assertThat(Base64Decoder.decode("TWFuUGln")).isEqualTo("ManPig");
    }

    @Test
    public void shouldEncodeAsciiString() throws Exception
    {
        assertThat(Base64Encoder.encode("secret message")).isEqualTo("c2VjcmV0IG1lc3NhZ2U=");
        assertThat(Base64Decoder.decode("c2VjcmV0IG1lc3NhZ2U=")).isEqualTo("secret message");
    }

    @Test
    public void shouldEncodeUtf8String() throws Exception
    {
        assertThat(Base64Encoder.encode("©£Æ")).isEqualTo("wqnCo8OG");
        assertThat(Base64Decoder.decode("wqnCo8OG")).isEqualTo("©£Æ");
    }

    @Test
    public void shouldEncodeEmptyString() throws Exception
    {
        assertThat(Base64Encoder.encode("")).isEmpty();
        assertThat(Base64Decoder.decode("")).isEqualTo("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotEncodeNullString() throws Exception
    {
        Base64Encoder.encode(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotDecodeNullString() throws Exception
    {
        Base64Decoder.decode(null);
    }
}