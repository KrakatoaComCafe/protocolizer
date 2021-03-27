//package br.com.krakatoa.protocolizer.model;
//
//import br.com.krakatoa.protocolizer.model.interpretmessage.MessageInterpreter;
//import br.com.krakatoa.protocolizer.model.interpretmessage.protocol.Protocol;
//import org.junit.jupiter.api.Test;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.TreeMap;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.mock;
//
//class MessageInterpreterTest {
//
//    @Test
//    void given_EmptyField_When_Generate_Then_ReturnEmptyString() {
//        Protocol protocol = mock(Protocol.class);
//        String bitmap = "5000000000000000";
//        String rawData = "12345123";
//        Map<String, String> fieldList = new TreeMap<>();
//        Map<String, String> fieldsWithNewValue = new HashMap<>();
//
//        MessageInterpreter messageInterpreter = new MessageInterpreter(protocol, bitmap, rawData, fieldList, fieldsWithNewValue);
//
//        String result = messageInterpreter.generateRawData();
//
//        assertNotNull(result);
//        assertEquals("", result);
//    }
//
//    @Test
//    void given_TwoFieldsThatExistsInTheRawData_When_Generate_Then_ReturnRawDataWithNewValues() {
//        Protocol protocol = mock(Protocol.class);
//        String bitmap = "5000000000000000";
//        String rawData = "12345123";
//        Map<String, String> fieldList = new TreeMap<>();
//        Map<String, String> fieldsWithNewValue = new HashMap<>();
//
//        fieldList.put("Field002", "12345");
//        fieldList.put("Field003", "123");
//        fieldList.put("Field004", "12");
//        fieldList.put("Field005", "12345");
//
//        fieldsWithNewValue.put("Field002", "54321");
//        fieldsWithNewValue.put("Field003", "321");
//
//        MessageInterpreter messageInterpreter = new MessageInterpreter(protocol, bitmap, rawData, fieldList, fieldsWithNewValue);
//
//        String result = messageInterpreter.generateRawData();
//
//        assertNotNull(result);
//        assertEquals("543213211212345", result);
//    }
//
//    @Test
//    void given_NoNewFieldValues_When_Generate_Then_ReturnOriginalRawData() {
//        Protocol protocol = mock(Protocol.class);
//        String bitmap = "5000000000000000";
//        String rawData = "12345123";
//        Map<String, String> fieldList = new TreeMap<>();
//        Map<String, String> fieldsWithNewValue = new HashMap<>();
//
//        fieldList.put("Field002", "12345");
//        fieldList.put("Field003", "123");
//        fieldList.put("Field004", "12");
//        fieldList.put("Field005", "12345");
//
//        MessageInterpreter messageInterpreter = new MessageInterpreter(protocol, bitmap, rawData, fieldList, fieldsWithNewValue);
//
//        String result = messageInterpreter.generateRawData();
//
//        assertNotNull(result);
//        assertEquals("123451231212345", result);
//    }
//
//    @Test
//    void given_NewValueFromFieldsThatAreNotPresentInTheRawData_When_Generate_Then_ReturnOriginalRawData() {
//        Protocol protocol = mock(Protocol.class);
//        String bitmap = "5000000000000000";
//        String rawData = "12345123";
//        Map<String, String> fieldList = new TreeMap<>();
//        Map<String, String> fieldsWithNewValue = new HashMap<>();
//
//        fieldList.put("Field002", "12345");
//        fieldList.put("Field003", "123");
//        fieldList.put("Field004", "12");
//        fieldList.put("Field005", "12345");
//
//        fieldsWithNewValue.put("Field023", "321");
//
//        MessageInterpreter messageInterpreter = new MessageInterpreter(protocol, bitmap, rawData, fieldList, fieldsWithNewValue);
//
//        String result = messageInterpreter.generateRawData();
//
//        assertNotNull(result);
//        assertEquals("123451231212345", result);
//    }
//    @Test
//    void given_NewValuesButNoOriginalFields_When_Generate_Then_ReturnEmptyString() {
//        Protocol protocol = mock(Protocol.class);
//        String bitmap = "5000000000000000";
//        String rawData = "12345123";
//        Map<String, String> fieldList = new TreeMap<>();
//        Map<String, String> fieldsWithNewValue = new HashMap<>();
//
//        fieldsWithNewValue.put("Field023", "321");
//
//        MessageInterpreter messageInterpreter = new MessageInterpreter(protocol, bitmap, rawData, fieldList, fieldsWithNewValue);
//
//        String result = messageInterpreter.generateRawData();
//
//        assertNotNull(result);
//        assertTrue(result.isEmpty());
//    }
//
//}