package br.com.krakatoa.protocolizer.entity.form;

import br.com.krakatoa.protocolizer.format.FieldType;
import br.com.krakatoa.protocolizer.repository.field.FieldEntity;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class FieldFormTest {

    @Test
    void given_FieldForm_When_convertToField_Then_ReturnField() {
        String name = "field003";
        FieldType type = FieldType.FIXED;
        int length = 3;
        int maxLength = 0;
        int minLength = 0;
        ProtocolEntity protocolEntity;
        protocolEntity = mock(ProtocolEntity.class);

        FieldForm fieldForm = new FieldForm(name, type, length, maxLength, minLength);
        FieldEntity fieldEntity = fieldForm.convertToField(protocolEntity);

        assertNotNull(fieldEntity);
        assertEquals("field003", fieldEntity.getName());
        assertEquals(3, fieldEntity.getLength());
    }
}