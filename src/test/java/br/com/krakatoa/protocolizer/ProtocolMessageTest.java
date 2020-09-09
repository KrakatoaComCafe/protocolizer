package br.com.krakatoa.protocolizer;

import br.com.krakatoa.protocolizer.controller.dto.ProtocolMessageDto;
import br.com.krakatoa.protocolizer.model.ProtocolMessage;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProtocolMessageTest {

    @Nested
    class ToDtoTest {

        @Test
        void given() {
            String expectedRaw = "ABCDEFABCDEFABCDEFABCDEFA";
            ProtocolMessage protocolMessage = ProtocolMessage.builder()
                    .raw(expectedRaw)
                    .terminal("12345678")
                    .terminalStartPosition(2)
                    .merchant("123456789012")
                    .merchantStartPosition(12)
                    .build();

            ProtocolMessageDto protocolMessageDto = protocolMessage.toDto();

            assertNotNull(protocolMessageDto);
            assertEquals(expectedRaw, protocolMessageDto.getRaw());
        }
    }

    @Nested
    class UpdateRawTest {

        @Test
        void given_MessageWithTerminalAndMerchant_When_UpdateRaw_Then_ReturnNewRaw() {
            String expected = "A12345678DE123456789012FA";
            ProtocolMessage protocolMessage = ProtocolMessage.builder()
                    .raw("ABCDEFABCDEFABCDEFABCDEFA")
                    .terminal("12345678")
                    .terminalStartPosition(2)
                    .merchant("123456789012")
                    .merchantStartPosition(12)
                    .build();

            String rawMessage = protocolMessage.updateRaw();

            assertEquals(expected, rawMessage);
        }
    }
}