package br.com.krakatoa.protocolizer.model;

import br.com.krakatoa.protocolizer.controller.dto.ProtocolMessageDto;
import br.com.krakatoa.protocolizer.format.ASCIIMessageFormat;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProtocolMessageTest {

    @Nested
    class ToDtoTest {

        @Test
        void given_MessageWithRaw_When_ToDto_Then_ReturnNewProtocolMessageDto() {
            String expectedRaw = "FFAAAAAAAAFFBBBBBBBBBBBBBFF";
            ProtocolMessage protocolMessage = ProtocolMessage.builder()
                    .raw(expectedRaw)
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
            String expected = "FFA94052125928425AFF3132333435363738FF313233343536373839303132FF";
            ProtocolMessage protocolMessage = ProtocolMessage.builder()
                    .raw("FF0000000000000000FF4141414141414141FF424242424242424242424242FF")
                    .bitmap("A94052125928425A")
                    .originalBitmap("0000000000000000")
                    .terminal("12345678")
                    .originalTerminal("AAAAAAAA")
                    .merchant("123456789012")
                    .originalMerchant("BBBBBBBBBBBB")
                    .messageFormat(new ASCIIMessageFormat())
                    .build();

            String rawMessage = protocolMessage.updateRaw();

            assertEquals(expected, rawMessage);
        }
    }
}