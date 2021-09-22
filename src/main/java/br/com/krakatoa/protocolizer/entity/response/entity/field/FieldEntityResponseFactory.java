package br.com.krakatoa.protocolizer.entity.response.entity.field;

import br.com.krakatoa.protocolizer.format.FieldType;
import br.com.krakatoa.protocolizer.repository.field.FieldEntity;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@NoArgsConstructor
@Component
public class FieldEntityResponseFactory {

    public FieldEntityResponse create(FieldEntity fieldEntity) {
        if(Objects.isNull(fieldEntity)) throw new FieldEntityResponseFactoryException("FieldEntity cannot be null");

        FieldType fieldType = fieldEntity.getType();
        switch (fieldType) {
            case FIXED:
                return new FixedEntityResponse(fieldEntity.getId(), fieldEntity.getName(), fieldType,
                    fieldEntity.getLength(), fieldEntity.getProtocol().getId());
            case LLVAR:
                return new VarEntityResponse(fieldEntity.getId(), fieldEntity.getName(), fieldType, 2,
                    fieldEntity.getMaxLength(), fieldEntity.getMinLength(), fieldEntity.getProtocol().getId());
            case LLLVAR:
                return new VarEntityResponse(fieldEntity.getId(), fieldEntity.getName(), fieldType, 3,
                    fieldEntity.getMaxLength(), fieldEntity.getMinLength(), fieldEntity.getProtocol().getId());
            case LLLLVAR:
                return new VarEntityResponse(fieldEntity.getId(), fieldEntity.getName(), fieldType, 4,
                    fieldEntity.getMaxLength(), fieldEntity.getMinLength(), fieldEntity.getProtocol().getId());
            default:
                throw new FieldEntityResponseFactoryException("FieldType unsupported +[" + fieldType + "]");
        }
    }

    static class FieldEntityResponseFactoryException extends RuntimeException {
        public FieldEntityResponseFactoryException(String message) {
            super(message);
        }
    }
}