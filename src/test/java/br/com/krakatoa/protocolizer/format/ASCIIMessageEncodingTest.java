package br.com.krakatoa.protocolizer.format;

import br.com.krakatoa.protocolizer.format.encoding.ASCIIMessageFormat;
import br.com.krakatoa.protocolizer.format.encoding.MessageFormat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ASCIIMessageEncodingTest {

    private final MessageFormat messageFormat;

    public ASCIIMessageEncodingTest() {
        this.messageFormat = new ASCIIMessageFormat();
    }

    @Test
    void given_StringValue_When_Execute_Then_ReturnInASCII() {
        String expected = "313233343536";
        String result = this.messageFormat.execute("123456");

        assertEquals(expected, result);
    }

}