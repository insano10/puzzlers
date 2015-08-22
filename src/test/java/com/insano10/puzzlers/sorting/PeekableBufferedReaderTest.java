package com.insano10.puzzlers.sorting;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.nio.file.Paths;

public class PeekableBufferedReaderTest
{
    @Test
    public void shouldBeAbleToRepeatedlyPeekTheSameLine() throws Exception
    {
        PeekableBufferedReader reader = new PeekableBufferedReader(Paths.get("src/test/resources/ExternalSort/peekableBufferedReader.txt"));

        Assertions.assertThat(reader.peekLine()).isEqualTo("line1");
        Assertions.assertThat(reader.peekLine()).isEqualTo("line1");
        Assertions.assertThat(reader.peekLine()).isEqualTo("line1");
    }

    @Test
    public void shouldBeAbleToReadTheNextLineByPolling() throws Exception
    {
        PeekableBufferedReader reader = new PeekableBufferedReader(Paths.get("src/test/resources/ExternalSort/peekableBufferedReader.txt"));

        Assertions.assertThat(reader.pollLine()).isEqualTo("line1");
        Assertions.assertThat(reader.pollLine()).isEqualTo("line2");
        Assertions.assertThat(reader.pollLine()).isEqualTo("line3");
    }

    @Test
    public void shouldBeAbleToPollTheLastPeekedLine() throws Exception
    {
        PeekableBufferedReader reader = new PeekableBufferedReader(Paths.get("src/test/resources/ExternalSort/peekableBufferedReader.txt"));

        Assertions.assertThat(reader.peekLine()).isEqualTo("line1");
        Assertions.assertThat(reader.peekLine()).isEqualTo("line1");
        Assertions.assertThat(reader.pollLine()).isEqualTo("line1");
    }

    @Test
    public void shouldGetNullWhenPeekingTheLastLineInTheFile() throws Exception
    {
        PeekableBufferedReader reader = new PeekableBufferedReader(Paths.get("src/test/resources/ExternalSort/peekableBufferedReader.txt"));

        Assertions.assertThat(reader.pollLine()).isEqualTo("line1");
        Assertions.assertThat(reader.pollLine()).isEqualTo("line2");
        Assertions.assertThat(reader.pollLine()).isEqualTo("line3");
        Assertions.assertThat(reader.pollLine()).isEqualTo("line4");
        Assertions.assertThat(reader.pollLine()).isEqualTo("line5");
        Assertions.assertThat(reader.peekLine()).isNull();
    }

    @Test
    public void shouldGetNullWhenPollingTheLastLineInTheFile() throws Exception
    {
        PeekableBufferedReader reader = new PeekableBufferedReader(Paths.get("src/test/resources/ExternalSort/peekableBufferedReader.txt"));

        Assertions.assertThat(reader.pollLine()).isEqualTo("line1");
        Assertions.assertThat(reader.pollLine()).isEqualTo("line2");
        Assertions.assertThat(reader.pollLine()).isEqualTo("line3");
        Assertions.assertThat(reader.pollLine()).isEqualTo("line4");
        Assertions.assertThat(reader.pollLine()).isEqualTo("line5");
        Assertions.assertThat(reader.pollLine()).isNull();
    }
}