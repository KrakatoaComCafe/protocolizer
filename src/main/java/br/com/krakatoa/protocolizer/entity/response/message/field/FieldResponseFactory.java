package br.com.krakatoa.protocolizer.entity.response.message.field;

import br.com.krakatoa.protocolizer.format.FieldType;
import br.com.krakatoa.protocolizer.entity.model.field.Field;

import java.util.Objects;

public class FieldResponseFactory {

    public FieldResponse create(Field field, String raw, int lastPos) {
        if(Objects.isNull(field) || Objects.isNull(raw)) throw new FieldResponseFactoryException("Passed null field");

        FieldType fieldType = field.getFieldType();

        switch (fieldType) {
            case FIXED:
                return new FixedResponse(field.getName(), raw.substring(lastPos, lastPos + field.getDataLength()));
            case LLVAR:
            case LLLVAR:
            case LLLLVAR:
                int dataStartPosition = lastPos + FieldType.getVliFromVarField(fieldType);
                String length = raw.substring(lastPos, dataStartPosition);
                String value = raw.substring(dataStartPosition, dataStartPosition +
                    Integer.parseInt(length));
                return new VarResponse(field.getName(), length, value);
            default:
                throw new FieldResponseFactoryException("Unknown value + [" + fieldType + "]");
        }
    }

    static class FieldResponseFactoryException extends RuntimeException {

        public FieldResponseFactoryException(String message) {
            super(message);
        }
    }
}