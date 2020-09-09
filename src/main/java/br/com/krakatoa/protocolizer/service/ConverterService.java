package br.com.krakatoa.protocolizer.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Map;
import java.util.TreeMap;

@Service
public class ConverterService {

    public String hexToBinary(String hexBitmap) {
        String value = new BigInteger(hexBitmap, 16).toString(2);
        int binaryBitmapLength = hexBitmap.length() * 4;
        String formatPad = "%" + binaryBitmapLength + "s";
        return String.format(formatPad, value).replace(" ", "0");
    }

    public Map<String, Boolean> binaryToMap(String binaryBitmap) {
        String format = "field%03d";
        Map<String, Boolean> bitmap = new TreeMap<>();
        for (int i = 0; i < binaryBitmap.length(); i++) {
            char currentChar = binaryBitmap.charAt(i);

            bitmap.put(String.format(format, i + 1), currentChar == '1');
        }

        return bitmap;
    }

    public String mapToBinary(Map<String, Boolean> bitmap) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Boolean> entry : bitmap.entrySet()) {
            boolean bit = entry.getValue();
            if (bit) {
                sb.append("1");
            } else {
                sb.append("0");
            }
        }

        return sb.toString();
    }

    public String binaryToHex(String binaryStr) {
        String hexBitmap = new BigInteger(binaryStr, 2).toString(16);
        int hexBitmapLength = binaryStr.length() / 4;
        String formatPad = "%" + hexBitmapLength + "s";
        return String.format(formatPad, hexBitmap).replace(" ", "0").toUpperCase();
    }
}
