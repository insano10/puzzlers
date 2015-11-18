package com.insano10.puzzlers.benchmarks;

import com.insano10.puzzlers.puzzles.codility.timecomplexity.FrogJmp;
import org.openjdk.jmh.annotations.Benchmark;

public class FrogJmpBenchmark
{
    @Benchmark
    public void benchmarkFrogJump() {

        FrogJmp.solution(10, 85, 30);
    }
}
