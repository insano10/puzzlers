package com.insano10.puzzlers.benchmarks;

import com.insano10.puzzlers.sorting.QuickSort;
import org.openjdk.jmh.annotations.Benchmark;

public class QuickSortBenchmark {

    @Benchmark
    public void benchmarkQuickSort() {

        QuickSort.sortWithExtraDataStructures(new char[]{'g', 's', 't', 'a', 'b', 'w', 'e'});
    }
}
