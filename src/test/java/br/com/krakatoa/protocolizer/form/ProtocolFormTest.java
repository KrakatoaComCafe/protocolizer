package br.com.krakatoa.protocolizer.form;

import br.com.krakatoa.protocolizer.format.encoding.Encoding;
import br.com.krakatoa.protocolizer.repository.field.Field;
import br.com.krakatoa.protocolizer.repository.protocol.Protocol;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProtocolFormTest {

    @Test
    void given_ProtocolForm_When_ConvertToProtocol_Then_ReturnProtocol() {
        String name = "Some Crazy Ass Protocol";
        String version = "21.1";
        Encoding encoding = Encoding.ASCII;

        HashMap<String, FieldForm> fieldFormMap = new HashMap<>();
        FieldForm fieldForm = Mockito.mock(FieldForm.class);
        fieldFormMap.put("Field002", fieldForm);
        fieldFormMap.put("Field003", fieldForm);

        ProtocolForm protocolForm = new ProtocolForm(name, version, encoding, fieldFormMap);
        Protocol protocol = protocolForm.convertToProtocol();

        assertNotNull(protocol);
        assertEquals("Some Crazy Ass Protocol", protocol.getName());
        assertEquals("21.1", protocol.getVersion());
        assertEquals(Encoding.ASCII, protocol.getEncoding());
        List<Field> fieldList = protocol.getFields();
        assertNotNull(fieldList);
        assertEquals(2, fieldList.size());
    }
}