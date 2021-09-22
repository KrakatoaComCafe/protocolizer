package br.com.krakatoa.protocolizer.entity.response.definition;

import br.com.krakatoa.protocolizer.format.FieldType;
import br.com.krakatoa.protocolizer.repository.field.FieldEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class FieldDefinitionResponseFactory {

    public FieldDefinitionResponse create(FieldEntity fieldEntity) {
        if (Objects.isNull(fieldEntity)) return null;

        switch (fieldEntity.getType()) {
            case FIXED:
                return new FixedDefinitionResponse(fieldEntity.getId(), fieldEntity.getName(), FieldType.FIXED,
                    fieldEntity.getLength(), fieldEntity.getProtocol().getId());
            case LLVAR:
                return new VarDefinitionResponse(fieldEntity.getId(), fieldEntity.getName(), FieldType.LLVAR, 2,
                    fieldEntity.getProtocol().getId());
            case LLLVAR:
                return new VarDefinitionResponse(fieldEntity.getId(), fieldEntity.getName(), FieldType.LLLVAR, 3,
                    fieldEntity.getProtocol().getId());
            case LLLLVAR:
                return new VarDefinitionResponse(fieldEntity.getId(), fieldEntity.getName(), FieldType.LLLLVAR, 4,
                    fieldEntity.getProtocol().getId());
            default:
                //TODO add logger here
                throw new IllegalStateException("Unexpected value: " + fieldEntity.getType());
        }
    }
}
