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
    private static final int MERGE_BUFFER_SIZE = 1024;

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

        List<BufferedReader> chunks = new ArrayList<>();
        for (Path chunkFile : chunkFiles)
        {
            chunks.add(new BufferedReader(new InputStreamReader(Files.newInputStream(chunkFile), Charset.forName("UTF-8")), MERGE_BUFFER_SIZE));
        }

        Path outputFilePath = Paths.get(filePath.getParent().toString(), filePath.getFileName().toString() + "_sorted");
        BufferedWriter outputBuffer = Files.newBufferedWriter(outputFilePath);

        String nextLine = getNextLine(chunks);
        while(nextLine != null)
        {
            outputBuffer.write(nextLine + "\n");
            nextLine = getNextLine(chunks);
        }

        for (Path chunkFile : chunkFiles)
        {
            Files.delete(chunkFile);
        }

        return outputFilePath;
    }

    private static String getNextLine(List<BufferedReader> chunks) throws IOException
    {
        String minLine = null;
        for (BufferedReader chunk : chunks)
        {
            chunk.mark(MERGE_BUFFER_SIZE);
            String line = chunk.readLine();

            if(minLine == null || line.compareTo(minLine) < 0)
            {
                minLine = line;
            }
            else
            {
                chunk.reset();
            }
        }

        return minLine;
    }

    //todo: performance of this probably sucks
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
