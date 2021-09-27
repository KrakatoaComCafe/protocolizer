package br.com.krakatoa.protocolizer.entity.response.message.field;

import br.com.krakatoa.protocolizer.entity.model.field.Field;
import br.com.krakatoa.protocolizer.format.FieldType;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class FieldResponseFactoryTest {

    private final FieldResponseFactory fieldResponseFactory;

    public FieldResponseFactoryTest() {
        this.fieldResponseFactory = new FieldResponseFactory();
    }

    @Test
    @DisplayName("Given null Field, then throw FieldResponseFactoryException")
    void given_nullField_When_Create_Then_ThrowFieldResponseFactoryException() {
        Throwable throwable = assertThrows(FieldResponseFactory.FieldResponseFactoryException.class,
            () -> this.fieldResponseFactory.create(null, Strings.EMPTY, 0));

        assertEquals("Passed null field", throwable.getMessage());
    }

    @Test
    @DisplayName("Given null raw message, then throw FieldResponseFactoryException")
    void given_nullRawMessage_When_Create_Then_ThrowFieldResponseFactoryException() {
        Field field = mock(Field.class);

        Throwable throwable = assertThrows(FieldResponseFactory.FieldResponseFactoryException.class,
            () -> this.fieldResponseFactory.create(field, null, 0));

        assertEquals("Passed null field", throwable.getMessage());
    }

    @Test
    @DisplayName("Given Fixed field, then return FixedResponse")
    void given_FieldWithFixedType_When_Create_Then_ReturnFixedResponse() {
        Field field = mock(Field.class);
        String raw = "123456";
        String fieldName = "potato";

        doReturn(FieldType.FIXED)
            .when(field)
            .getFieldType();
        doReturn(fieldName)
            .when(field)
            .getName();
        doReturn(6)
            .when(field)
            .getDataLength();

        FieldResponse fieldResponse = this.fieldResponseFactory.create(field, raw, 0);

        assertTrue(fieldResponse instanceof  FixedResponse);
    }

    @Test
    @DisplayName("Given LLVAR field, then return VarResponse")
    void given_FieldWithFixedType_When_Create_Then_() {
        Field field = mock(Field.class);
        String raw = "0812345678";
        String fieldName = "kartofel";

        doReturn(FieldType.LLVAR)
            .when(field)
            .getFieldType();
        doReturn(fieldName)
            .when(field)
            .getName();

        FieldResponse fieldResponse = this.fieldResponseFactory.create(field, raw, 0);

        assertTrue(fieldResponse instanceof  VarResponse);
    }
}