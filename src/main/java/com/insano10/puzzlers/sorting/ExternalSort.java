package com.insano10.puzzlers.sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExternalSort
{
    private static final int NUM_CHUNKS = 10;
    private static final int MERGE_BUFFER_SIZE_BYTES = 1024 * 1024 * 10;

    /**
     * sort an arbitrary sized file (line by line) using a 2-pass sort and merge
     * storing the sorted file at filePath_sorted
     * @param filePath - the file to be sorted
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
                Path chunkFile = Files.createTempFile("externalSort-" + i, ".tmp");
                Files.write(chunkFile, sortedChunkLines);

                chunkFiles.add(chunkFile);
            }
        }

        //create readers for each chunk file
        List<PeekableBufferedReader> chunksReaders = new ArrayList<>();
        for (Path chunkFile : chunkFiles)
        {
            chunksReaders.add(new PeekableBufferedReader(chunkFile, MERGE_BUFFER_SIZE_BYTES));
        }

        //merge each chunk into a single output file
        Path outputFilePath = Paths.get(filePath.getParent().toString(), filePath.getFileName().toString() + ".sorted");
        try(BufferedWriter outputBuffer = Files.newBufferedWriter(outputFilePath))
        {
            String nextLine = getNextLine(chunksReaders);
            while(nextLine != null)
            {
                outputBuffer.write(nextLine + "\n");
                nextLine = getNextLine(chunksReaders);
            }
        }

        //clean up
        for (PeekableBufferedReader chunksReader : chunksReaders)
        {
            chunksReader.close();
        }
        for (Path chunkFile : chunkFiles)
        {
            Files.delete(chunkFile);
        }

        return outputFilePath;
    }

    private static List<String> getChunkLines(int chunkSizeBytes, BufferedReader reader) throws IOException
    {
        List<String> chunkLines = new ArrayList<>();
        int totalBytesRead = 0;

        while(totalBytesRead < chunkSizeBytes)
        {
            String line = reader.readLine();

            if(line == null)
            {
                break;
            }

            chunkLines.add(line);
            totalBytesRead += line.getBytes(Charset.forName("UTF-8")).length;
        }
        return chunkLines;
    }

    private static String getNextLine(List<PeekableBufferedReader> chunkReaders) throws IOException
    {
        String minLine = null;
        int readerWithNextLineIdx = -1;
        for (int i = 0; i < chunkReaders.size(); i++)
        {
            String line = chunkReaders.get(i).peekLine();

            if(minLine == null || (line != null && line.compareTo(minLine) < 0))
            {
                minLine = line;
                readerWithNextLineIdx = i;
            }
        }

        return chunkReaders.get(readerWithNextLineIdx).pollLine();
    }
}
