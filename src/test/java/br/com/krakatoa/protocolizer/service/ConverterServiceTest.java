package br.com.krakatoa.protocolizer.service;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConverterServiceTest {

    @Nested
    class HexToListOfBitmapPresent {
        private ConverterService converterService;

        HexToListOfBitmapPresent() {
            this.converterService = new ConverterService();
        }

        @Test
        @DisplayName("Bitmap string return activated fields")
        void given_HexValue_When_HexToListOfBitmapPresent_Then_ReturnListOfPresentBitMap() {
            List<String> expectList = new ArrayList<>();
            expectList.add("field002");
            expectList.add("field006");
            expectList.add("field011");
            expectList.add("field013");
            expectList.add("field017");
            expectList.add("field019");
            expectList.add("field021");
            expectList.add("field022");
            expectList.add("field025");
            expectList.add("field034");
            expectList.add("field038");
            expectList.add("field042");
            expectList.add("field046");
            expectList.add("field051");
            expectList.add("field058");
            expectList.add("field062");

            List<String> bitmapPresent = this.converterService.hexToListOfBitmapPresent("4428AC8044442044");

            assertNotNull(bitmapPresent);
            assertEquals(16, bitmapPresent.size());
            assertArrayEquals(expectList.toArray(), bitmapPresent.toArray());
        }

        @Test
        @DisplayName("Empty Bitmap should return an empty list")
        void given_EmptyBitmap_When_hexToListOfBitmapPresent_Then_ReturnEmptyList() {
            List<String> bitmapActivated = this.converterService.hexToListOfBitmapPresent("0000000000000000");

            assertNotNull(bitmapActivated);
            assertTrue(bitmapActivated.isEmpty());
        }

        @Test
        @DisplayName("Null Bitmap should return empty list")
        void given_NullBitmapString_When_hexToListOfBitmapPresent_Then_ReturnEmptyList() {
            List<String> bitmapActivated = this.converterService.hexToListOfBitmapPresent(null);

            assertNotNull(bitmapActivated);
            assertTrue(bitmapActivated.isEmpty());
        }

        @Test
        @DisplayName("Bitmap with non hexadecimal value should return empty list")
        void given_BitmapWithNonHexadecimalValue_When_hexToListOfBitmapPresent_Then_ReturnEmptyList() {
            List<String> bitmapActivated = this.converterService.hexToListOfBitmapPresent("000000J000000000");

            assertNotNull(bitmapActivated);
            assertTrue(bitmapActivated.isEmpty());
        }
    }

    @Nested
    class HexToMapOfBitsTest {

        private ConverterService converterService;

        HexToMapOfBitsTest() {
            this.converterService = new ConverterService();
        }

        @Test
        void given_HexValue_When_HexToMapOfBits_Then_ReturnMapOfBits() {
            Map<String, Boolean> mapOfBits = this.converterService.hexToMapOfBits("4428AC8044442044");

            assertFalse(mapOfBits.get("field001"));
            assertTrue(mapOfBits.get("field002"));
            assertFalse(mapOfBits.get("field003"));
            assertFalse(mapOfBits.get("field004"));
            assertFalse(mapOfBits.get("field005"));
            assertTrue(mapOfBits.get("field006"));
            assertFalse(mapOfBits.get("field007"));
            assertFalse(mapOfBits.get("field008"));
            assertFalse(mapOfBits.get("field009"));
            assertFalse(mapOfBits.get("field010"));
        }
    }

    @Nested
    class HexToBinaryTest {

        private ConverterService converterService;

        HexToBinaryTest() {
            this.converterService = new ConverterService();
        }

        @Test
        @DisplayName("String of bitmap should return String of bits")
        void given_HexValue_When_hexToBinary_Then_ReturnBinary() {
            String binary = this.converterService.hexToBinary("4428AC8044442044");

            assertEquals("0100010000101000101011001000000001000100010001000010000001000100", binary);
        }

        @Test
        void given_NullBitmap_When_hexToBinary_Then_ReturnEmptyString() {
            String binary = this.converterService.hexToBinary(null);

            assertNotNull(binary);
            assertTrue(binary.isEmpty());
        }

        @Test
        void given_EmptyBitmapString_When_hexToBinary_Then_ReturnEmptyString() {
            String binary = this.converterService.hexToBinary(Strings.EMPTY);

            assertNotNull(binary);
            assertTrue(binary.isEmpty());
        }
    }

    @Nested
    class BinaryToMapTest {

        private ConverterService converterService;

        BinaryToMapTest() {
            this.converterService = new ConverterService();
        }

        @Test
        @DisplayName("String of bits should return Map of bits")
        void given_BinaryString_When_BinaryToMap_Then_ReturnBitmap() {
            Map<String, Boolean> bitmap = this.converterService.binaryToMap("0100010000101000101011001000000001000100010001000010000001000100");

            assertFalse(bitmap.get("field001"));
            assertTrue(bitmap.get("field002"));
            assertFalse(bitmap.get("field003"));
            assertFalse(bitmap.get("field004"));
            assertFalse(bitmap.get("field005"));
            assertTrue(bitmap.get("field006"));
            assertFalse(bitmap.get("field007"));
            assertFalse(bitmap.get("field008"));
            assertFalse(bitmap.get("field009"));
            assertFalse(bitmap.get("field010"));
        }

        @Test
        @DisplayName("Null value should return empty Map")
        void given_NullStringOfBits_When_binaryToMap_Then_ReturnEmptyMap() {
            Map<String, Boolean> bitmap = this.converterService.binaryToMap(null);

            assertNotNull(bitmap);
            assertTrue(bitmap.isEmpty());
        }

        @Test
        @DisplayName("Empty String should return empty Map")
        void given_EmptyString_When_binaryToMap_Then_ReturnEmptyMap() {
            Map<String, Boolean> bitmap = this.converterService.binaryToMap(Strings.EMPTY);

            assertNotNull(bitmap);
            assertTrue(bitmap.isEmpty());
        }

        @Test
        @DisplayName("String with non binary value should return empty Map")
        void given_StringWithNonBinaryValue_When_binaryToMap_Then_ReturnEmptyMap() {
            Map<String, Boolean> bitmap = this.converterService.binaryToMap("0102");
            assertNotNull(bitmap);
        }
    }

    @Nested
    class MapToBinaryTest {

        private ConverterService converterService;

        MapToBinaryTest() {
            this.converterService = new ConverterService();
        }

        @Test
        @DisplayName("Map Fields should return Bitmap in String of activated bits")
        void given_MapOfBoolean_When_mapToBinary_Then_ReturnStringWithBinary() {
            Map<String, Boolean> bitmap = new TreeMap<>();
            bitmap.put("field001", false);
            bitmap.put("field002", true);
            bitmap.put("field003", false);
            bitmap.put("field004", false);
            bitmap.put("field005", false);
            bitmap.put("field006", true);
            bitmap.put("field007", false);
            bitmap.put("field008", false);
            bitmap.put("field009", false);
            bitmap.put("field010", false);
            bitmap.put("field011", true);
            bitmap.put("field012", false);
            bitmap.put("field013", true);
            bitmap.put("field014", false);
            bitmap.put("field015", false);
            bitmap.put("field016", false);

            String binaryBitmap = this.converterService.mapToBinary(bitmap);

            assertEquals("0100010000101000", binaryBitmap);
        }

        @Test
        @DisplayName("Null Bitmap should return Empty String")
        void given_NullBitmap_When_mapToBinary_Then_ReturnEmptyString() {
            String binaryBitmap = this.converterService.mapToBinary(null);

            assertNotNull(binaryBitmap);
            assertTrue(binaryBitmap.isEmpty());
        }

        @Test
        @DisplayName("Empty Map should return Empty String")
        void given_EmptyMap_When_mapToBinary_Then_ReturnEmptyString() {
            Map<String, Boolean> bitmap = new TreeMap<>();
            String binaryBitmap = this.converterService.mapToBinary(bitmap);

            assertNotNull(binaryBitmap);
            assertTrue(binaryBitmap.isEmpty());
        }

        @Test
        @DisplayName("Incorrect Field Name should return Empty String")
        void given_IncorrectFieldName_When_isBitmapValid_Then_ReturnEmptyString() {
            Map<String, Boolean> bitmap = new TreeMap<>();
            bitmap.put("field01", false);
            bitmap.put("field002", true);
            bitmap.put("field003", false);
            bitmap.put("field004", false);

            String binaryBitmap = this.converterService.mapToBinary(bitmap);

            assertNotNull(binaryBitmap);
            assertEquals(Strings.EMPTY, binaryBitmap);
        }
    }

    @Nested
    class BinaryToHexTest {
        private ConverterService converterService;

        BinaryToHexTest() {
            this.converterService = new ConverterService();
        }

        @Test
        @DisplayName("Bitmap string is returned from a binary String")
        void given_BinaryStringWith1sAnd0s_When_binaryToHex_Then_ReturnBitmapInString() {
            String binaryStr = "1010100101000000010100100001001001011001001010000100001001011010";

            String hexBitmap = this.converterService.binaryToHex(binaryStr);

            assertEquals("A94052125928425A", hexBitmap);
        }

        @Test
        @DisplayName("Null entry should return Bitmap with no active bits")
        void given_NullString_When_binaryToHex_Then_ReturnBitmapWithoutActivatedBits() {
            String hexBitmap = this.converterService.binaryToHex(null);

            assertNotNull(hexBitmap);
            assertEquals("0000000000000000", hexBitmap);
        }

        @Test
        void given_LessThan64BitsInTheBinaryString_When_binaryToHex_Then_ReturnBitmapWithoutActivatedBits() {
            String binaryStr = "10101001010000000101001000010010";

            String hexBitmap = this.converterService.binaryToHex(binaryStr);

            assertNotNull(hexBitmap);
            assertEquals("0000000000000000", hexBitmap);
        }

        //TODO throw this error until it reaches the controller and return something readable to the user
        @Test
        void given_StringWithNonBinaryValues_When_binaryToHex_Then_ThrowNumberFormatException() {
            String binaryStr = "10101001010000000101001000010010010110010010100001000010010aabaa";

            assertThrows(NumberFormatException.class, () ->
                this.converterService.binaryToHex(binaryStr)
            );
        }
    }
}