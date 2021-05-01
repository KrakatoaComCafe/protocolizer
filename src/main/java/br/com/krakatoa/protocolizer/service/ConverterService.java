package br.com.krakatoa.protocolizer.service;

import org.apache.logging.log4j.util.Strings;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class ConverterService {

    private static final String NAME_FORMAT = "field%03d";
    private static final short HEXADECIMAL_RADIX = 16;
    private static final short BINARY_RADIX = 2;
    private static final short BITMAP_IN_BITS_MAX_LENGTH = 64;
    private static final String EMPTY_BITMAP = "0000000000000000";

    public List<String> hexToListOfBitmapPresent(String bitMapInHex) {
        if (Objects.isNull(bitMapInHex) || !this.isBitmapStringValid(bitMapInHex)) return Collections.emptyList();

        String binaryBitmap = this.hexToBinary(bitMapInHex);
        return this.binaryToListOfBitmapPresent(binaryBitmap);
    }

    public Map<String, Boolean> hexToMapOfBits(String bitMapInHex) {
        String binaryBitmap = this.hexToBinary(bitMapInHex);
        return this.binaryToMap(binaryBitmap);
    }

    public String hexToBinary(String hexBitmap) {
        if(!this.isHexToBinaryValid(hexBitmap)) return Strings.EMPTY;

        String value = new BigInteger(hexBitmap, HEXADECIMAL_RADIX).toString(BINARY_RADIX);
        int binaryBitmapLength = hexBitmap.length() * 4;
        String formatPad = "%" + binaryBitmapLength + "s";
        return String.format(formatPad, value).replace(" ", "0");
    }

    //TODO maybe a different class for each validation
    private boolean isHexToBinaryValid(String hexBitmap) {
        if(Objects.isNull(hexBitmap)) return false;
        if(hexBitmap.isEmpty()) return false;
        return true;
    }

    public Map<String, Boolean> binaryToMap(String binaryBitmap) {
        if(Objects.isNull(binaryBitmap)) return Collections.emptyMap();

        Map<String, Boolean> bitmap = new TreeMap<>();
        for (int i = 0; i < binaryBitmap.length(); i++) {
            char currentChar = binaryBitmap.charAt(i);

            // TODO make this throw an exception
            if(currentChar != '1' && currentChar != '0') return Collections.emptyMap();

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
        if (!this.isBitmapValid(bitmap)) return Strings.EMPTY;

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
        if (!this.isBinaryStringValid(binaryStr)) return EMPTY_BITMAP;

        String hexBitmap = new BigInteger(binaryStr, BINARY_RADIX).toString(HEXADECIMAL_RADIX);
        int hexBitmapLength = binaryStr.length() / 4;
        String formatPad = "%" + hexBitmapLength + "s";
        return String.format(formatPad, hexBitmap).replace(" ", "0").toUpperCase();
    }

    //TODO validation should be a different class, package private
    //TODO maybe a different class for each validation
    private boolean isBitmapStringValid(String bitmapStr) {
        if (bitmapStr.length() != 16) return false;

        try {
            Long.parseLong(bitmapStr, HEXADECIMAL_RADIX);
            return true;
        } catch (NumberFormatException ex) {
            //TODO throw exception until it reaches the controller and return an appropriate response
            return false;
        }
    }

    private boolean isBinaryStringValid(String binaryStr) {
        //TODO throw exception until it reaches the controller and return an appropriate response
        if (Objects.isNull(binaryStr)) return false;
        return binaryStr.length() == BITMAP_IN_BITS_MAX_LENGTH;
    }

    private boolean isBitmapValid(Map<String, Boolean> bitmap) {
        //TODO throw exception until it reaches the controller and return an appropriate response
        if (Objects.isNull(bitmap)) return false;
        if (bitmap.isEmpty()) return false;

        for(Map.Entry<String, Boolean> entry : bitmap.entrySet()) {
            if(!this.isFormatValid(entry.getKey())) return false;
        }

        return true;
    }

    private boolean isFormatValid(String str) {
        try{
            if(!str.startsWith("field")) return false;
            if(!str.substring(5,8).matches("[0-9]{3}")) return false;
        }catch (IndexOutOfBoundsException e) {
            // TODO return this exception to controller with a correct message for the user
            return false;
        }

        return true;
    }
}
