package com.insano10.puzzlers.sorting;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExternalSort
{
    private static final int NUM_CHUNKS = 10;
    private static final int MERGE_BUFFER_SIZE = 100;

    /**
     * sort an arbitrary sized file (line by line) using a 2-pass sort and merge
     * storing the sorted file at filePath_sorted
     * @param filePath
     */
    public static Path sort(Path filePath) throws IOException
    {
        long fileSizeBytes = Files.size(filePath);
        int chunkSizeBytes = (int)Math.ceil(fileSizeBytes/ NUM_CHUNKS);
        List<Path> chunkFiles = new ArrayList<>();

        //sort the file into many separate chunk files
        try(BufferedReader reader = Files.newBufferedReader(filePath))
        {
            for (int i = 0; i < NUM_CHUNKS; i++)
            {
                List<String> chunkLines = getChunkLines(chunkSizeBytes, reader);
                List<String> sortedChunkLines = QuickSort.sortWithArrayLists(chunkLines);
                Path chunkFile = Files.createTempFile("externalSort-" + i, "tmp");
                Files.write(chunkFile, sortedChunkLines);

                chunkFiles.add(chunkFile);
            }
        }

        //read in the first set of lines into the chunk buffers
        List<BufferedReader> chunksReaders = new ArrayList<>();
        List<String[]> chunkBuffers = new ArrayList<>();
        for (Path chunkFile : chunkFiles)
        {
            BufferedReader chunkReader = new BufferedReader(new InputStreamReader(Files.newInputStream(chunkFile), Charset.forName("UTF-8")));

            String[] chunkBuffer = new String[MERGE_BUFFER_SIZE];
            String line = chunkReader.readLine();
            for (int i = 0; i < MERGE_BUFFER_SIZE; i++)
            {
                if(line == null) break;
                chunkBuffer[i] = line;

                line = chunkReader.readLine();
            }
            chunkBuffers.add(chunkBuffer);
            chunksReaders.add(chunkReader);
        }

        Path outputFilePath = Paths.get(filePath.getParent().toString(), filePath.getFileName().toString() + "_sorted");
        try(BufferedWriter outputBuffer = Files.newBufferedWriter(outputFilePath))
        {
            String nextLine = getNextLine(chunkBuffers, chunksReaders);
            while(nextLine != null)
            {
                outputBuffer.write(nextLine + "\n");
                nextLine = getNextLine(chunkBuffers, chunksReaders);
            }
        }

        for (Path chunkFile : chunkFiles)
        {
            Files.delete(chunkFile);
        }

        return outputFilePath;
    }

    private static String getNextLine(List<String[]> chunkBuffers, List<BufferedReader> chunkReaders) throws IOException
    {
        int bufferIndex = 0;
        String minLine = null;

        for (String[] chunkBuffer : chunkBuffers)
        {
            int nextLineIndex = getNextLineIndexFromBuffer(chunkBuffers, chunkReaders, bufferIndex);
            String nextLine = chunkBuffer[nextLineIndex];
            if(minLine == null || (nextLine != null && nextLine.compareTo(minLine) < 0))
            {
                minLine = nextLine;
                chunkBuffer[nextLineIndex] = null;
            }

            bufferIndex++;
        }

        return minLine;
    }

    private static void refillBuffer(List<String[]> chunkBuffers, List<BufferedReader> chunkReaders, int bufferIndex) throws IOException
    {
        BufferedReader bufferedReader = chunkReaders.get(bufferIndex);
        String[] buffer = chunkBuffers.get(bufferIndex);

        for (int i = 0; i < buffer.length; i++)
        {
            buffer[i] = bufferedReader.readLine();
        }
    }

    private static int getNextLineIndexFromBuffer(List<String[]> chunkBuffers, List<BufferedReader> chunkReaders, int bufferIndex) throws IOException
    {
        int lineIndex = -1;
        for (String line : chunkBuffers.get(bufferIndex))
        {
            lineIndex++;
            if(line != null)
            {
                break;
            }
        }

        if(lineIndex == -1)
        {
            refillBuffer(chunkBuffers, chunkReaders, bufferIndex);
            return getNextLineIndexFromBuffer(chunkBuffers, chunkReaders, bufferIndex);
        }
        return lineIndex;
    }

    private static List<String> getChunkLines(int chunkSizeBytes, BufferedReader reader) throws IOException
    {
        List<String> chunkLines = new ArrayList<>();
        int totalBytesRead = 0;
        String line = reader.readLine();

        while(line != null && totalBytesRead < chunkSizeBytes)
        {
            chunkLines.add(line);
            totalBytesRead += line.getBytes(Charset.forName("UTF-8")).length;
            line = reader.readLine();
        }
        return chunkLines;
    }
}
