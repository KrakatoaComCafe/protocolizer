package br.com.krakatoa.protocolizer.format;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MessageFormatFactoryTest {

    @Test
    void given_ASCIIFormat_When_Get_Then_ReturnASCIIMessageFormat() {
        MessageFormat messageFormat = MessageFormatFactory.get(Format.ASCII);
        Assertions.assertTrue(messageFormat instanceof ASCIIMessageFormat);
    }

}