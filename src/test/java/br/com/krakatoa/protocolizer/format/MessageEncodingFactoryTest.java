package br.com.krakatoa.protocolizer.format;

import br.com.krakatoa.protocolizer.format.encoding.ASCIIMessageFormat;
import br.com.krakatoa.protocolizer.format.encoding.Encoding;
import br.com.krakatoa.protocolizer.format.encoding.MessageFormat;
import br.com.krakatoa.protocolizer.format.encoding.MessageFormatFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MessageEncodingFactoryTest {

    @Test
    void given_ASCIIFormat_When_Get_Then_ReturnASCIIMessageFormat() {
        MessageFormat messageFormat = MessageFormatFactory.get(Encoding.ASCII);
        Assertions.assertTrue(messageFormat instanceof ASCIIMessageFormat);
    }

}