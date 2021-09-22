package br.com.krakatoa.protocolizer.entity.response.message.field;

import br.com.krakatoa.protocolizer.format.FieldType;
import br.com.krakatoa.protocolizer.entity.model.field.Field;

public class FieldResponseFactory {

    public FieldResponse create(Field field, String raw, int lastPos) {
        FieldType fieldType = field.getFieldType();

        switch (fieldType) {
            case FIXED:
                return new FixedResponse(field.getName(), raw.substring(lastPos, lastPos + field.getLength()));
            case LLVAR:
                int dataStartPosition = lastPos + 2;
                String length = raw.substring(lastPos, dataStartPosition);
                String value = raw.substring(dataStartPosition, lastPos +
                    Integer.parseInt(raw.substring(lastPos, lastPos + field.getLength())));
                return new VarResponse(field.getName(), length, value);
            default:
                throw new FieldResponseFactoryException("Unknown value + [" + fieldType + "]");
        }
    }
}

class FieldResponseFactoryException extends RuntimeException {

    public FieldResponseFactoryException(String message) {
        super(message);
    }
}