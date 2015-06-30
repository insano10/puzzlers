package com.insano10.puzzlers.benchmarks;

import com.insano10.puzzlers.sorting.QuickSort;
import org.openjdk.jmh.annotations.Benchmark;

import java.util.Arrays;

public class QuickSortBenchmark {

    @Benchmark
    public void benchmarkQuickSortWithExtraDataStructures() {

        QuickSort.sortWithExtraDataStructures(new char[]{'g', 's', 't', 'a', 'b', 'w', 'e'});
    }

    @Benchmark
    public void benchmarkQuickSortWithArrayLists() {

        QuickSort.sortWithArrayLists(Arrays.asList('g', 's', 't', 'a', 'b', 'w', 'e'));
    }
}
