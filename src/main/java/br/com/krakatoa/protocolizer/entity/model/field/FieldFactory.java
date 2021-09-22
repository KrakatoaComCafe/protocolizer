package br.com.krakatoa.protocolizer.entity.model.field;

import br.com.krakatoa.protocolizer.format.FieldType;
import br.com.krakatoa.protocolizer.repository.field.FieldEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FieldFactory {

    public Field create(FieldEntity fieldEntity) {
        FieldType type = fieldEntity.getType();
        switch (type) {
            case FIXED:
                return new FixedField(fieldEntity.getName(), fieldEntity.getLength());
            case LLVAR:
                return new VarField(fieldEntity.getName(), 2, fieldEntity.getLength());
            case LLLVAR:
                return new VarField(fieldEntity.getName(), 3, fieldEntity.getLength());
            case LLLLVAR:
                return new VarField(fieldEntity.getName(), 4, fieldEntity.getLength());
            default:
                throw new FieldFactoryException("Unknown type used ["+ type +"]");
        }
    }

    //TODO this method does not create anything, should it be here?
    public List<Field> createList(List<FieldEntity> fieldEntityList) {
        return fieldEntityList.stream()
            .map(this::create)
            .collect(Collectors.toList());
    }
}

class FieldFactoryException extends RuntimeException {

    public FieldFactoryException(String message) {
        super(message);
    }
}
