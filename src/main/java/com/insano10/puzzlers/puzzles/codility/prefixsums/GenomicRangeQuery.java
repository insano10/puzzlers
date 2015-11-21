package com.insano10.puzzlers.puzzles.codility.prefixsums;

public class GenomicRangeQuery
{
    //A,C,G,T = [1,2,3,4]
    public int[] solution(String S, int[] P, int[] Q)
    {
        int[] distanceToNextA = calculateSuffixSumFor('A', S);
        int[] distanceToNextC = calculateSuffixSumFor('C', S);
        int[] distanceToNextG = calculateSuffixSumFor('G', S);
        int[] distanceToNextT = calculateSuffixSumFor('T', S);

        int numQueries = P.length;
        int[] queryResults = new int[numQueries];

        for (int i = 0; i < numQueries; i++)
        {
            int lower = P[i];
            int upper = Q[i];

            if(distanceToNextA[lower] <= (upper-lower))
            {
                queryResults[i] = 1;
            }
            else if(distanceToNextC[lower] <= (upper-lower))
            {
                queryResults[i] = 2;
            }
            else if(distanceToNextG[lower] <= (upper-lower))
            {
                queryResults[i] = 3;
            }
            else if(distanceToNextT[lower] <= (upper-lower))
            {
                queryResults[i] = 4;
            }
        }

        return queryResults;
    }

    private int[] calculateSuffixSumFor(char nucleotide, String sequence)
    {
        int[] distanceToNext = new int[sequence.length()];

        distanceToNext[sequence.length()-1] = sequence.charAt(sequence.length()-1) == nucleotide ? 0 : 1;

        for (int i = sequence.length()-2; i >= 0; i--)
        {
            distanceToNext[i] = sequence.charAt(i) == nucleotide ? 0 : distanceToNext[i+1] + 1;
        }
        return distanceToNext;
    }
}
