package br.com.krakatoa.protocolizer.form;

import br.com.krakatoa.protocolizer.model.Field;
import br.com.krakatoa.protocolizer.model.Protocol;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FieldFormTest {

    @Test
    void given_FieldForm_When_convertToField_Then_ReturnField() {
        String name = "Field003";
        int minLength = 3;
        int maxLength = 6;
        Protocol protocol;
        protocol = Mockito.mock(Protocol.class);

        FieldForm fieldForm = new FieldForm(name, minLength, maxLength);
        Field field = fieldForm.convertToField(protocol);

        assertNotNull(field);
        assertEquals("Field003", field.getName());
        assertEquals(3, field.getMinLength());
        assertEquals(6, field.getMaxLength());
    }
}