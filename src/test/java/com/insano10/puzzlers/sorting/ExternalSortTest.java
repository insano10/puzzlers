package com.insano10.puzzlers.sorting;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExternalSortTest
{
    @Ignore
    @Test
    public void shouldSortFile() throws Exception
    {
        Path sortedFile = ExternalSort.sort(Paths.get("src/test/resources/ExternalSort/fileToSort.txt"));

        assertFileSorted(sortedFile);
    }

    private void assertFileSorted(Path sortedFile) throws IOException
    {
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
}