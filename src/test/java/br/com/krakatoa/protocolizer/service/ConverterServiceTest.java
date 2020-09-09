package br.com.krakatoa.protocolizer.service;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConverterServiceTest {

    @Nested
    class HexToBinaryTest {

        private ConverterService converterService;

        HexToBinaryTest() {
            this.converterService = new ConverterService();
        }

        @Test
        void given_HexValue_When_hexToBinary_Then_ReturnBinary() {
            String binary = this.converterService.hexToBinary("4428AC8044442044");

            assertEquals("0100010000101000101011001000000001000100010001000010000001000100", binary);
        }
    }

    @Nested
    class BinaryToMapTest {

        private ConverterService converterService;

        BinaryToMapTest() {
            this.converterService = new ConverterService();
        }

        @Test
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
    }

    @Nested
    class MapToBinaryTest {

        private ConverterService converterService;

        MapToBinaryTest() {
            this.converterService = new ConverterService();
        }

        @Test
        void given_MapOfBoolean_When_mapToBinary_Then_ReturnStringWithBinary() {
            Map<String, Boolean> bitmap = new TreeMap<>();
            bitmap.put("Field001", false);
            bitmap.put("Field002", true);
            bitmap.put("Field003", false);
            bitmap.put("Field004", false);
            bitmap.put("Field005", false);
            bitmap.put("Field006", true);
            bitmap.put("Field007", false);
            bitmap.put("Field008", false);
            bitmap.put("Field009", false);
            bitmap.put("Field010", false);
            bitmap.put("Field011", true);
            bitmap.put("Field012", false);
            bitmap.put("Field013", true);
            bitmap.put("Field014", false);
            bitmap.put("Field015", false);
            bitmap.put("Field016", false);

            String binaryBitmap = this.converterService.mapToBinary(bitmap);

            assertEquals("0100010000101000", binaryBitmap);
        }
    }

    @Nested
    class BinaryToHexTest {
        private ConverterService converterService;

        BinaryToHexTest() {
            this.converterService = new ConverterService();
        }

        @Test
        void given() {
            String binaryStr = "1010100101000000010100100001001001011001001010000100001001011010";

            String hexBitmap = this.converterService.binaryToHex(binaryStr);

            assertEquals("A94052125928425A", hexBitmap);
        }
    }
}