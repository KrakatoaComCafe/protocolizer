package br.com.krakatoa.protocolizer.form;

import br.com.krakatoa.protocolizer.format.encoding.Encoding;
import br.com.krakatoa.protocolizer.repository.field.FieldEntity;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolEntity;
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
        ProtocolEntity protocolEntity = protocolForm.convertToProtocol();

        assertNotNull(protocolEntity);
        assertEquals("Some Crazy Ass Protocol", protocolEntity.getName());
        assertEquals("21.1", protocolEntity.getVersion());
        assertEquals(Encoding.ASCII, protocolEntity.getEncoding());
        List<FieldEntity> fieldEntityList = protocolEntity.getFieldEntityEntities();
        assertNotNull(fieldEntityList);
        assertEquals(2, fieldEntityList.size());
    }
}