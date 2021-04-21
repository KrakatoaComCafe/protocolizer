package br.com.krakatoa.protocolizer.service;

import br.com.krakatoa.protocolizer.controller.dto.message.MessageDTO;
import br.com.krakatoa.protocolizer.form.MessageForm;
import br.com.krakatoa.protocolizer.format.encoding.Encoding;
import br.com.krakatoa.protocolizer.repository.field.FieldEntity;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolDataProvider;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InterpretMessageServiceTest {

    @Nested
    class InterpretTest {

        private final ProtocolDataProvider protocolDataProvider;
        private final ConverterService converterService;
        private final InterpretMessageService interpretMessageService;
        private final MessageForm messageForm;

        InterpretTest() {
            this.protocolDataProvider = mock(ProtocolDataProvider.class);
            this.converterService = mock(ConverterService.class);
            this.interpretMessageService = new InterpretMessageService(this.protocolDataProvider, this.converterService);

            this.messageForm = mock(MessageForm.class);
        }

        @Test
        @DisplayName("Happy Flow - Sending message with fields 02 e 04, return MessageDto with both fields")
        void given_AllValidAttributesFromMessageForm_When_interpret_Then_ReturnMessageDto() {
            String protocolName = "A ISO 8583 look alike";
            String protocolVersion = "21.1";
            String bitmap = "5000000000000000";
            String rawMessage = "12345123";
            Encoding encoding = Encoding.ASCII;
            MessageForm messageForm = mock(MessageForm.class);
            List<String> fieldsActivated = Arrays.asList("Field002", "Field004");
            ProtocolEntity protocolEntity = mock(ProtocolEntity.class);
            FieldEntity fieldEntity = mock(FieldEntity.class);
            List<FieldEntity> protocolFieldEntityEntities = Arrays.asList(fieldEntity, fieldEntity, fieldEntity);

            when(messageForm.getBitmap())
                .thenReturn(bitmap);
            when(this.converterService.hexToListOfBitmapPresent(messageForm.getBitmap()))
                .thenReturn(fieldsActivated);
            when(messageForm.getProtocol())
                .thenReturn(protocolName);
            when(messageForm.getVersion())
                .thenReturn(protocolVersion);
            when(this.protocolDataProvider.findOneByNameAndVersion(messageForm.getProtocol(), messageForm.getVersion()))
                .thenReturn(protocolEntity);
            when(protocolEntity.getFieldEntityList())
                .thenReturn(protocolFieldEntityEntities);
            when(messageForm.getRawData())
                .thenReturn(rawMessage);
            when(messageForm.getEncoding())
                .thenReturn(encoding);
            when(fieldEntity.getLength())
                .thenReturn(5)
                .thenReturn(5)
                .thenReturn(3);
            when(fieldEntity.getName())
                .thenReturn("Field002")
                .thenReturn("Field003")
                .thenReturn("Field004")
                .thenReturn("Field002")
                .thenReturn("Field004");

            MessageDTO messageDTO = this.interpretMessageService.interpret(messageForm);

            assertNotNull(messageDTO);
            assertEquals("A ISO 8583 look alike", messageDTO.getProtocol());
            assertEquals("21.1", messageDTO.getVersion());
            assertEquals("5000000000000000", messageDTO.getBitmap());
            assertEquals(Encoding.ASCII, messageDTO.getEncoding());
            assertNotNull(messageDTO.getFields());
            assertEquals(2, messageDTO.getFields().size());
            assertEquals("12345", messageDTO.getFieldValues().get(0));
            assertEquals("123", messageDTO.getFieldValues().get(1));
            assertEquals("Field002", messageDTO.getFieldNames().get(0));
            assertEquals("Field004", messageDTO.getFieldNames().get(1));
        }

        @Test
        void given_EmptyMessage_When_interpret_Then_ReturnMessageDtoWithNoFields() {
            String protocolName = "A ISO 8583 look alike";
            String protocolVersion = "21.1";
            String bitmap = "0000000000000000";
            String rawMessage = "";
            Encoding encoding = Encoding.ASCII;
            MessageForm messageForm = mock(MessageForm.class);
            List<String> fieldsActivated = Collections.emptyList();
            ProtocolEntity protocolEntity = mock(ProtocolEntity.class);
            FieldEntity fieldEntity = mock(FieldEntity.class);
            List<FieldEntity> protocolFieldEntityEntities = Collections.emptyList();

            when(messageForm.getBitmap())
                .thenReturn(bitmap);
            when(this.converterService.hexToListOfBitmapPresent(messageForm.getBitmap()))
                .thenReturn(fieldsActivated);
            when(messageForm.getProtocol())
                .thenReturn(protocolName);
            when(messageForm.getVersion())
                .thenReturn(protocolVersion);
            when(this.protocolDataProvider.findOneByNameAndVersion(messageForm.getProtocol(), messageForm.getVersion()))
                .thenReturn(protocolEntity);
            when(protocolEntity.getFieldEntityList())
                .thenReturn(protocolFieldEntityEntities);
            when(messageForm.getRawData())
                .thenReturn(rawMessage);
            when(messageForm.getEncoding())
                .thenReturn(encoding);
            when(fieldEntity.getLength())
                .thenReturn(5)
                .thenReturn(5)
                .thenReturn(3);
            when(fieldEntity.getName())
                .thenReturn("Field002")
                .thenReturn("Field003")
                .thenReturn("Field004")
                .thenReturn("Field002")
                .thenReturn("Field004");

            MessageDTO messageDTO = this.interpretMessageService.interpret(messageForm);

            assertNotNull(messageDTO);
            assertEquals("A ISO 8583 look alike", messageDTO.getProtocol());
            assertEquals("21.1", messageDTO.getVersion());
            assertEquals("0000000000000000", messageDTO.getBitmap());
            assertEquals(Encoding.ASCII, messageDTO.getEncoding());
            assertNotNull(messageDTO.getFields());
            assertNotNull(messageDTO.getFields());
            assertTrue(messageDTO.getFields().isEmpty());
        }

        @Test
        @DisplayName("Return MessageDto with no fields when no definitions are found")
        void given_NoDefinitionForFields_When_interpret_Then_ReturnMessageDtoWithNoFields() {
            String protocolName = "A ISO 8583 look alike";
            String protocolVersion = "21.1";
            String bitmap = "5000000000000000";
            String rawMessage = "12345123";
            Encoding encoding = Encoding.ASCII;
            MessageForm messageForm = mock(MessageForm.class);
            List<String> fieldsActivated = Arrays.asList("Field002", "Field004");
            ProtocolEntity protocolEntity = mock(ProtocolEntity.class);
            List<FieldEntity> protocolFieldEntityEntities = Collections.emptyList();

            when(messageForm.getBitmap())
                .thenReturn(bitmap);
            when(this.converterService.hexToListOfBitmapPresent(messageForm.getBitmap()))
                .thenReturn(fieldsActivated);
            when(messageForm.getProtocol())
                .thenReturn(protocolName);
            when(messageForm.getVersion())
                .thenReturn(protocolVersion);
            when(this.protocolDataProvider.findOneByNameAndVersion(messageForm.getProtocol(), messageForm.getVersion()))
                .thenReturn(protocolEntity);
            when(protocolEntity.getFieldEntityList())
                .thenReturn(protocolFieldEntityEntities);
            when(messageForm.getRawData())
                .thenReturn(rawMessage);
            when(messageForm.getEncoding())
                .thenReturn(encoding);

            MessageDTO messageDTO = this.interpretMessageService.interpret(messageForm);

            assertNotNull(messageDTO);
            assertEquals("A ISO 8583 look alike", messageDTO.getProtocol());
            assertEquals("21.1", messageDTO.getVersion());
            assertEquals("5000000000000000", messageDTO.getBitmap());
            assertEquals(Encoding.ASCII, messageDTO.getEncoding());
            assertNotNull(messageDTO.getFields());
            assertTrue(messageDTO.getFields().isEmpty());
        }
    }
}