package br.com.krakatoa.protocolizer.format;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ASCIIMessageFormatTest {

    private final MessageFormat messageFormat;

    public ASCIIMessageFormatTest() {
        this.messageFormat = new ASCIIMessageFormat();
    }

    @Test
    void given_StringValue_When_Execute_Then_ReturnInASCII() {
        String expected = "313233343536";
        String result = this.messageFormat.execute("123456");

        assertEquals(expected, result);
    }

}