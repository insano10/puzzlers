package com.insano10.puzzlers.sorting;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class ExternalSortTest
{
    @Ignore
    @Test
    public void shouldSortFile() throws Exception
    {
        Path filePath = Paths.get("src/test/resources/ExternalSort/fileToSort.txt");

        Path sortedFile = ExternalSort.sort(filePath);

        assertFileSorted(filePath, sortedFile);
    }

    private void assertFileSorted(Path originalFilePath, Path sortedFile) throws IOException
    {
        long originalLineCount = getLineCount(originalFilePath);
        long sortedLineCount = getLineCount(sortedFile);

        assertThat(sortedLineCount).isEqualTo(originalLineCount);

        String lastLine;
        String currentLine;
        try(BufferedReader bufferedReader = Files.newBufferedReader(sortedFile))
        {
            lastLine = bufferedReader.readLine();
            currentLine = bufferedReader.readLine();

            while(currentLine != null)
            {
                if(currentLine.compareTo(lastLine) < 0)
                {
                    Assertions.fail(String.format("Line %s is after line %s but it should be before it according to sort order", currentLine, lastLine));
                }
                currentLine = bufferedReader.readLine();
            }
        }
    }

    private long getLineCount(Path filePath) throws IOException
    {
        long lineCount = 0;
        try(BufferedReader reader = Files.newBufferedReader(filePath, Charset.forName("UTF-8")))
        {
            while(reader.readLine() != null)
            {
                lineCount++;
            }
        }
        return lineCount;
    }
}