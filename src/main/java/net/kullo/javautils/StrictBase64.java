/*
 * Copyright 2014–2016 The javautils contributors (see NOTICE.txt)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.kullo.javautils;

import java.nio.charset.StandardCharsets;

public class StrictBase64  {
    public static class DecodingException extends Exception {
        public DecodingException(String message) {
            super(message);
        }
    }

    public static class InvalidInputLengthException extends DecodingException {
        public InvalidInputLengthException(String message) {
            super(message);
        }
    }

    public static class InvalidInputCharacterException extends DecodingException {
        public InvalidInputCharacterException(String message) {
            super(message);
        }
    }

    private final static char[] ENCODE_ALPHABET = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U',
            'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u',
            'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', '+', '/'
    };

    /**
     * Translates a Base64 value to either its 6-bit reconstruction value
     * or a negative number indicating some other meaning.
     **/
    private final static byte[] DECODE_ALPHABET = {
            -9,-9,-9,-9,-9,-9,-9,-9,-9,                 // Decimal  0 -  8
            -5,-5,                                      // Whitespace: Tab and Linefeed
            -9,-9,                                      // Decimal 11 - 12
            -5,                                         // Whitespace: Carriage Return
            -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 14 - 26
            -9,-9,-9,-9,-9,                             // Decimal 27 - 31
            -5,                                         // Whitespace: Space
            -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,              // Decimal 33 - 42
            62,                                         // Plus sign at decimal 43
            -9,-9,-9,                                   // Decimal 44 - 46
            63,                                         // Slash at decimal 47
            52,53,54,55,56,57,58,59,60,61,              // Numbers zero through nine
            -9,-9,-9,                                   // Decimal 58 - 60
            -1,                                         // Equals sign at decimal 61
            -9,-9,-9,                                   // Decimal 62 - 64
            0,1,2,3,4,5,6,7,8,9,10,11,12,13,            // Letters 'A' through 'N'
            14,15,16,17,18,19,20,21,22,23,24,25,        // Letters 'O' through 'Z'
            -9,-9,-9,-9,-9,-9,                          // Decimal 91 - 96
            26,27,28,29,30,31,32,33,34,35,36,37,38,     // Letters 'a' through 'm'
            39,40,41,42,43,44,45,46,47,48,49,50,51,     // Letters 'n' through 'z'
            -9,-9,-9,-9,-9,                             // Decimal 123 - 127
            -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,        // Decimal 128 - 139
            -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 140 - 152
            -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 153 - 165
            -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 166 - 178
            -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 179 - 191
            -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 192 - 204
            -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 205 - 217
            -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 218 - 230
            -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,     // Decimal 231 - 243
            -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9         // Decimal 244 - 255
    };

    public static byte[] decode(String input) throws DecodingException {
        return decode(input, false);
    }

    public static byte[] decode(String input, boolean ignoreWhitespace) throws DecodingException {
        if (ignoreWhitespace) {
            StringBuilder noWhitespace = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                char currentChar = input.charAt(i);
                if (currentChar != ' ' && currentChar != '\n' && currentChar != '\r' && currentChar != '\t') {
                    noWhitespace.append(currentChar);
                }
            }
            if (input.length() != noWhitespace.length()) {
                input = noWhitespace.toString();
            }
        }

        if (input.length() % 4 != 0) {
            throw new InvalidInputLengthException("Input length is not a multiple of 4");
        }

        int outLength = ((input.length() * 3) / 4) -
                (input.indexOf('=') > 0 ? (input.length() - input.indexOf('=')) : 0);
        byte decoded[] = new byte[outLength];

        int outPos = 0;
        int inBlock[] = new int[4];
        for (int inPos = 0; inPos < input.length(); inPos += 4) {
            inBlock[0] = DECODE_ALPHABET[input.charAt(inPos+0)];
            inBlock[1] = DECODE_ALPHABET[input.charAt(inPos+1)];
            inBlock[2] = DECODE_ALPHABET[input.charAt(inPos+2)];
            inBlock[3] = DECODE_ALPHABET[input.charAt(inPos+3)];

            // Regular base64 range (a-zA-Z0-9+/): 0–63
            // Padding character (=): -1
            if (inBlock[0] < -1 || inBlock[1] < -1 || inBlock[2] < -1 || inBlock[3] < -1) {
                throw new InvalidInputCharacterException("Invalid input character");
            }

            decoded[outPos++] = (byte) ((inBlock[0] << 2) | (inBlock[1] >> 4));
            if (inBlock[2] != -1) {
                decoded[outPos++] = (byte) ((inBlock[1] << 4) | (inBlock[2] >> 2));
                if (inBlock[3] != -1) {
                    decoded[outPos++] = (byte) ((inBlock[2] << 6) | inBlock[3]);
                }
            }
        }

        return decoded;
    }

    public static String encode(byte[] in)       {
        StringBuffer out = new StringBuffer((in.length * 4) / 3);

        int block;

        for (int i = 0; i < in.length; i += 3) {
            block = (in[i] & 0xFC) >> 2;
            out.append(ENCODE_ALPHABET[block]);

            block = (in[i] & 0x03) << 4;
            if (i + 1 < in.length)      {
                block |= (in[i + 1] & 0xF0) >> 4;
                out.append(ENCODE_ALPHABET[block]);
                block = (in[i + 1] & 0x0F) << 2;
                if (i + 2 < in.length)  {
                    block |= (in[i + 2] & 0xC0) >> 6;
                    out.append(ENCODE_ALPHABET[block]);
                    block = in[i + 2] & 0x3F;
                    out.append(ENCODE_ALPHABET[block]);
                } else  {
                    out.append(ENCODE_ALPHABET[block]);
                    out.append('=');
                }
            } else {
                out.append(ENCODE_ALPHABET[block]);
                out.append("==");
            }
        }

        return out.toString();
    }
}
