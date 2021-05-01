package br.com.krakatoa.protocolizer.model.interpretmessage;

import br.com.krakatoa.protocolizer.form.MessageChangeForm;
import br.com.krakatoa.protocolizer.model.interpretmessage.messagefields.MessageValues;
import br.com.krakatoa.protocolizer.model.interpretmessage.messagefields.MessageValuesFactory;
import br.com.krakatoa.protocolizer.model.interpretmessage.protocol.Protocol;
import br.com.krakatoa.protocolizer.model.interpretmessage.protocol.ProtocolFactory;
import br.com.krakatoa.protocolizer.model.interpretmessage.protocol.fielddefinition.FieldDefinition;
import br.com.krakatoa.protocolizer.model.interpretmessage.protocol.fielddefinition.FieldDefinitionFactory;
import br.com.krakatoa.protocolizer.model.interpretmessage.protocol.fielddefinition.FixedDefinition;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class MessageInterpretFactoryTest {

    private final FieldDefinitionFactory fieldDefinitionFactory;
    private final ProtocolFactory protocolFactory;
    private final MessageInterpretFactory messageInterpretFactory;
    private final MessageValuesFactory messageValuesFactory;

    public MessageInterpretFactoryTest() {
        this.fieldDefinitionFactory = mock(FieldDefinitionFactory.class);
        this.protocolFactory = mock(ProtocolFactory.class);
        this.messageValuesFactory = mock(MessageValuesFactory.class);
        this.messageInterpretFactory = new MessageInterpretFactory(this.fieldDefinitionFactory, this.protocolFactory, this.messageValuesFactory);
    }

    @Test
    @DisplayName("Null MessageChangeForm should throw RuntimeException")
    void given_NullMessageChangeForm_When_create_Then_ThrowRuntimeException() {
        ProtocolEntity protocolEntity = mock(ProtocolEntity.class);

        assertThrows(RuntimeException.class,
            () -> this.messageInterpretFactory.create(protocolEntity, null));
    }

    @Test
    @DisplayName("Null ProtocolEntity should throw RuntimeException")
    void given_NullProtocolEntity_When_create_Then_ThrowRuntimeException() {
        MessageChangeForm messageChangeForm = mock(MessageChangeForm.class);

        assertThrows(RuntimeException.class,
            () -> this.messageInterpretFactory.create(null, messageChangeForm));
    }

    @Test
    @DisplayName("Given a field to change value, update raw message with the new value")
    void given_OneNewField_When_create_Then_ReturnStringWithOverwriteFieldValue() {
        ProtocolEntity protocolEntity = mock(ProtocolEntity.class);
        MessageChangeForm messageChangeForm = mock(MessageChangeForm.class);

        FieldDefinition fieldDefinition01 = mock(FixedDefinition.class);
        FieldDefinition fieldDefinition02 = mock(FixedDefinition.class);
        List<FieldDefinition> fieldDefinitionList = Arrays.asList(fieldDefinition01, fieldDefinition02);
        Protocol protocol = mock(Protocol.class);
        MessageValues messageValues = mock(MessageValues.class);
        String bitmap = "5000000000000000";
        String rawData = "12345123";
        Map<String, String> currentFieldValues = new TreeMap<>();
        currentFieldValues.put("field002", "12345");
        currentFieldValues.put("field004", "123");
        Map<String, String> newFieldsValues = new TreeMap<>();
        newFieldsValues.put("field004", "111");

        doReturn(fieldDefinitionList)
            .when(this.fieldDefinitionFactory)
            .createList(protocolEntity);
        doReturn(protocol)
            .when(this.protocolFactory)
            .create(protocolEntity, fieldDefinitionList);
        doReturn(messageValues)
            .when(this.messageValuesFactory)
            .create(messageChangeForm, fieldDefinitionList);
        doReturn(bitmap)
            .when(messageChangeForm)
            .getBitmap();
        doReturn(rawData)
            .when(messageChangeForm)
            .getRawData();
        doReturn(currentFieldValues)
            .when(messageChangeForm)
            .getFields();
        doReturn(newFieldsValues)
            .when(messageValues)
            .generateFieldsWithNewValue(currentFieldValues);
        doReturn(currentFieldValues)
            .when(messageValues)
            .getFieldList();

        MessageInterpreter messageInterpreter = this.messageInterpretFactory.create(protocolEntity, messageChangeForm);

        assertNotNull(messageInterpreter);
        assertEquals("12345111", messageInterpreter.generateRawData());
    }
}