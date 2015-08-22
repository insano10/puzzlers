package com.insano10.puzzlers.sorting;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class PeekableBufferedReader implements Closeable
{
    private final BufferedReader reader;
    private String nextLine;

    public PeekableBufferedReader(Path file, final int bufferSizeBytes) throws IOException
    {
        reader = new BufferedReader(new InputStreamReader(Files.newInputStream(file), Charset.forName("UTF-8")), bufferSizeBytes);
    }

    public String peekLine() throws IOException
    {
        if(nextLine == null)
        {
            nextLine = reader.readLine();
        }
        return nextLine;
    }

    public String pollLine() throws IOException
    {
        String lineToReturn = peekLine();
        nextLine = reader.readLine();

        return lineToReturn;
    }

    @Override
    public void close() throws IOException
    {
        reader.close();
    }
}
