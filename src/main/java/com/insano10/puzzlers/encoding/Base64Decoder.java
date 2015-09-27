package com.insano10.puzzlers.encoding;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Base64Decoder
{
    private static final String OUTPUT_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    public static String decode(String str)
    {
        if (str == null)
        {
            throw new IllegalArgumentException("cannot decode a null string");
        }

        char[] chars = str.toCharArray();
        byte[] encodedBytes = new byte[chars.length];

        for (int i = 0; i < chars.length; i++)
        {
            encodedBytes[i] = (byte) OUTPUT_CHARS.indexOf(chars[i]);
        }

        //take groups of 4, lower 6 bits of each byte and stick together
        //we can assume that the number of bytes divides neatly into 4 (otherwise it wasn't encoded properly)

        int encodedByteIdx = 0;
        int decodedByteIdx = 0;

        byte[] decodedBytes = new byte[(encodedBytes.length / 4) * 3];

        while (encodedByteIdx < encodedBytes.length)
        {
            int padCount = 0;
            int shiftIdx = 0;
            int combinedBytes = 0;
            for (int i = encodedByteIdx + 3; i >= encodedByteIdx; i--)
            {
                if (encodedBytes[i] < 0)
                {
                    //found a char that is not in OUTPUT_CHARS
                    padCount++;
                }

                byte trimmed = (byte) (encodedBytes[i] & 63);
                combinedBytes += trimmed << (6 * shiftIdx);
                shiftIdx++;
            }
            encodedByteIdx += 4;

            //combinedBytes should now have 24 bits in it
            //slice it into 8 bit chunks and these are our characters


            //first char will never be padding
            decodedBytes[decodedByteIdx] = (byte) ((combinedBytes >> 16) & 255);
            decodedByteIdx++;

            if (padCount < 2)
            {
                //second char could be padding
                decodedBytes[decodedByteIdx] = (byte) ((combinedBytes >> 8) & 255);
                decodedByteIdx++;
            }
            if (padCount == 0)
            {
                //third char could be padding
                decodedBytes[decodedByteIdx] = (byte) (combinedBytes & 255);
                decodedByteIdx++;
            }
        }

        return new String(Arrays.copyOfRange(decodedBytes, 0, decodedByteIdx), StandardCharsets.UTF_8);
    }
}
