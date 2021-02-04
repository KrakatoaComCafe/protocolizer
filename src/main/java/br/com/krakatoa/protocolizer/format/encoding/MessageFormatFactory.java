package br.com.krakatoa.protocolizer.format.encoding;

public class MessageFormatFactory {

    private static final String ERROR_MESSAGE = "Format not supported";

    private MessageFormatFactory() {

    }

    public static MessageFormat get(Encoding encoding) {
        switch (encoding) {
            case ASCII:
                return new ASCIIMessageFormat();
            default:
                try {
                    throw new MessageFormatException(ERROR_MESSAGE);
                } catch (MessageFormatException e) {
                    e.printStackTrace();
                    return null;
                }
        }
    }
}
