package com.insano10.puzzlers.encoding;

public class Base64
{
    private static final String OUTPUT_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    public static String encode(String str)
    {
        if(str == null)
        {
            throw new IllegalArgumentException("Cannot encode null string");
        }

        if(str.isEmpty())
        {
            return str;
        }

        StringBuilder encodedString = new StringBuilder();

        byte[] bytes = str.getBytes();

        int bufferIdx = 0;
        byte[] buffer = new byte[3];

        for (byte b : bytes)
        {
            if (bufferIdx == buffer.length)
            {
                encodedString.append(encodeBuffer(buffer, bufferIdx));
                bufferIdx = 0;
            }

            buffer[bufferIdx] = b;
            bufferIdx++;
        }
        //encode the remainder
        encodedString.append(encodeBuffer(buffer, bufferIdx));

        return encodedString.toString();
    }

    private static String encodeBuffer(byte[] buffer, int bufferLength)
    {
        char[] outputChars = new char[4];

        if(bufferLength == 1)
        {
            //e.g. 11011010 = 110110 100000
            outputChars[0] = OUTPUT_CHARS.charAt((buffer[0] & 255) >> 2); //top 6 bits (mask to lose any potential sign)
            outputChars[1] = OUTPUT_CHARS.charAt(buffer[0] << 4 & 63); //lower 2 bits (at the start of the 6 bits)
            outputChars[2] ='=';
            outputChars[3] ='=';
        }
        else if(bufferLength == 2)
        {
            //e.g. 11011010 11011010 = 110110 101101 101000
            outputChars[0] = OUTPUT_CHARS.charAt((buffer[0] & 255) >> 2); //top 6 bits from byte0
            outputChars[1] = OUTPUT_CHARS.charAt(buffer[0] << 4 & 63 | (buffer[1] & 255) >> 4); //lower 2 bits from byte0 and top 4 bits from byte1
            outputChars[2] = OUTPUT_CHARS.charAt(buffer[1] << 2 & 63); //lower 4 bits from byte1
            outputChars[3] ='=';
        }
        else if(bufferLength == 3)
        {
            //e.g. 11011010 11011010 00010100 = 110110 101101 101000 010100
            outputChars[0] = OUTPUT_CHARS.charAt((buffer[0] & 255) >> 2); //top 6 bits from byte0
            outputChars[1] = OUTPUT_CHARS.charAt(buffer[0] << 4 & 63 | (buffer[1] & 255) >> 4); //lower 2 bits from byte0 and top 4 bits from byte1
            outputChars[2] = OUTPUT_CHARS.charAt(buffer[1] << 2 & 63 | (buffer[2] & 255) >> 6); //lower 4 bits from byte1 and top 2 bits from byte2
            outputChars[3] = OUTPUT_CHARS.charAt(buffer[2] & 63); //lower 6 bits from byte2
        }

        return new String(outputChars);
    }

    /*
        Negative byte numbers:

        bytes in java are signed

        e.g. -62 = 11000010

        shifting right 2 places will give 11110000 (-16) as the arithmetic shift operator >> maintains the sign

        we cannot index into our codes with a minus index so we need to make the byte unsigned.
        we do this by bitmasking it with 11111111 to turn -62 into 194

        this works because 11000010 & 255 first casts the number to an int (00000000 00000000 00000000 11000010) then ANDs it with 255
        thus eliminating the sign.

     */
}
