package br.com.krakatoa.protocolizer.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ConverterService {

    private static final String NAME_FORMAT = "Field%03d";

    public List<String> hexToListOfBitmapPresent(String bitMapInHex) {
        String binaryBitmap = this.hexToBinary(bitMapInHex);
        return this.binaryToListOfBitmapPresent(binaryBitmap);
    }

    public Map<String, Boolean> hexToMapOfBits(String bitMapInHex) {
        String binaryBitmap = this.hexToBinary(bitMapInHex);
        return this.binaryToMap(binaryBitmap);
    }

    public String hexToBinary(String hexBitmap) {
        String value = new BigInteger(hexBitmap, 16).toString(2);
        int binaryBitmapLength = hexBitmap.length() * 4;
        String formatPad = "%" + binaryBitmapLength + "s";
        return String.format(formatPad, value).replace(" ", "0");
    }

    public Map<String, Boolean> binaryToMap(String binaryBitmap) {
        Map<String, Boolean> bitmap = new TreeMap<>();
        for (int i = 0; i < binaryBitmap.length(); i++) {
            char currentChar = binaryBitmap.charAt(i);

            bitmap.put(String.format(NAME_FORMAT, i + 1), currentChar == '1');
        }

        return bitmap;
    }

    public List<String> binaryToListOfBitmapPresent(String binaryBitmap) {
        List<String> presentFieldList = new ArrayList<>();
        for (int i = 0; i < binaryBitmap.length(); i++) {
            char bit = binaryBitmap.charAt(i);
            if (bit == '1') presentFieldList.add(String.format(NAME_FORMAT, i + 1));
        }

        return presentFieldList;
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
