package com.insano10.puzzlers.sorting;

import java.nio.file.Path;

public class ExternalSort
{
    /**
     * sort an arbitrary sized file (line by line) using a 2-pass sort and merge
     * storing the sorted file at filePath_sorted
     * @param filePath
     */
    public static void sort(Path filePath)
    {
        //1. sort
        //read in 100MB of data from the file (divide the whole file size into 10 for the chunk size?)
        //sort it (quicksort)
        //write it to a tmp file

        //repeat until all data has been sorted into chunk files (should have 10 chunk files)

        //2. merge
        //read the first 10MB of each chunk into input buffers
        //merge sort them into an output buffer
        //flush the output buffer to file once full
        //fetch more data from each chunk file when the input buffer is drained
        //delete the chunk files
    }
}
