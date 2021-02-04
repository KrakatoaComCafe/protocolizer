package br.com.krakatoa.protocolizer.service;

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
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConverterServiceTest {

    @Nested
    class HexToListOfBitmapPresent {
        private ConverterService converterService;

        HexToListOfBitmapPresent() {
            this.converterService = new ConverterService();
        }

        @Test
        void given_HexValu_When_HhxToListOfBitmapPresent_Then_ReturnListOfPresentBitMap() {
            List<String> expectList = new ArrayList<>();
            expectList.add("Field002");
            expectList.add("Field006");
            expectList.add("Field011");
            expectList.add("Field013");
            expectList.add("Field017");
            expectList.add("Field019");
            expectList.add("Field021");
            expectList.add("Field022");
            expectList.add("Field025");
            expectList.add("Field034");
            expectList.add("Field038");
            expectList.add("Field042");
            expectList.add("Field046");
            expectList.add("Field051");
            expectList.add("Field058");
            expectList.add("Field062");

            List<String> bitmapPresent = this.converterService.hexToListOfBitmapPresent("4428AC8044442044");

            assertNotNull(bitmapPresent);
            assertEquals(16, bitmapPresent.size());
            assertArrayEquals(expectList.toArray(), bitmapPresent.toArray());
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

            assertFalse(mapOfBits.get("Field001"));
            assertTrue(mapOfBits.get("Field002"));
            assertFalse(mapOfBits.get("Field003"));
            assertFalse(mapOfBits.get("Field004"));
            assertFalse(mapOfBits.get("Field005"));
            assertTrue(mapOfBits.get("Field006"));
            assertFalse(mapOfBits.get("Field007"));
            assertFalse(mapOfBits.get("Field008"));
            assertFalse(mapOfBits.get("Field009"));
            assertFalse(mapOfBits.get("Field010"));
        }
    }

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

            assertFalse(bitmap.get("Field001"));
            assertTrue(bitmap.get("Field002"));
            assertFalse(bitmap.get("Field003"));
            assertFalse(bitmap.get("Field004"));
            assertFalse(bitmap.get("Field005"));
            assertTrue(bitmap.get("Field006"));
            assertFalse(bitmap.get("Field007"));
            assertFalse(bitmap.get("Field008"));
            assertFalse(bitmap.get("Field009"));
            assertFalse(bitmap.get("Field010"));
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