package br.com.krakatoa.protocolizer.form;

import br.com.krakatoa.protocolizer.repository.field.Field;
import br.com.krakatoa.protocolizer.repository.protocol.Protocol;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class FieldFormTest {

    @Test
    void given_FieldForm_When_convertToField_Then_ReturnField() {
        String name = "Field003";
        int length = 3;
        Protocol protocol;
        protocol = mock(Protocol.class);

        FieldForm fieldForm = new FieldForm(name, length);
        Field field = fieldForm.convertToField(protocol);

        assertNotNull(field);
        assertEquals("Field003", field.getName());
        assertEquals(3, field.getLength());
    }
}