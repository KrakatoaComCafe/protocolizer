package br.com.krakatoa.protocolizer.service;

import br.com.krakatoa.protocolizer.controller.dto.message.MessageDTO;
import br.com.krakatoa.protocolizer.form.MessageForm;
import br.com.krakatoa.protocolizer.format.encoding.Encoding;
import br.com.krakatoa.protocolizer.repository.field.Field;
import br.com.krakatoa.protocolizer.repository.protocol.Protocol;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolDataProvider;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InterpretMessageServiceTest {

    @Nested
    class InterpretTest {

        private final ProtocolDataProvider protocolDataProvider;
        private final ConverterService converterService;
        private final InterpretMessageService interpretMessageService;

        InterpretTest() {
            this.protocolDataProvider = mock(ProtocolDataProvider.class);
            this.converterService = mock(ConverterService.class);
            this.interpretMessageService = new InterpretMessageService(this.protocolDataProvider, this.converterService);
        }

        @Test
        void given_AllValidAttributesFromMessageForm_When_interpretMessage_Then_ReturnMessageDto() {
            String protocolName = "A ISO 8583 look alike";
            String protocolVersion = "21.1";
            String bitmap = "5000000000000000";
            String rawMessage = "12345123";
            Encoding encoding = Encoding.ASCII;
            MessageForm messageForm = mock(MessageForm.class);
            List<String> fieldList = Arrays.asList("Field002", "Field004");
            Protocol protocol = mock(Protocol.class);
            Field field = mock(Field.class);
            List<Field> protocolFields = Arrays.asList(field, field, field);

            when(messageForm.getBitmap())
                    .thenReturn(bitmap);
            when(this.converterService.hexToListOfBitmapPresent(messageForm.getBitmap()))
                    .thenReturn(fieldList);
            when(messageForm.getProtocol())
                    .thenReturn(protocolName);
            when(messageForm.getVersion())
                    .thenReturn(protocolVersion);
            when(this.protocolDataProvider.findOneByNameAndVersion(messageForm.getProtocol(), messageForm.getVersion()))
                    .thenReturn(protocol);
            when(protocol.getFields())
                    .thenReturn(protocolFields);
            when(messageForm.getRawData())
                    .thenReturn(rawMessage);
            when(messageForm.getEncoding())
                    .thenReturn(encoding);
            when(field.getLength())
                    .thenReturn(5)
                    .thenReturn(5)
                    .thenReturn(3);
            when(field.getName())
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
    }
}