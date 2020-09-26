package br.com.krakatoa.protocolizer.format;

public class MessageFormatFactory {

    private static final String ERROR_MESSAGE = "Format not supported";

    private MessageFormatFactory() {

    }

    public static MessageFormat get(Format format) {
        switch (format) {
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
