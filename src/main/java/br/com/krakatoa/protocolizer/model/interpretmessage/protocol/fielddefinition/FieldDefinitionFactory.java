package br.com.krakatoa.protocolizer.model.interpretmessage.protocol.fielddefinition;

import br.com.krakatoa.protocolizer.repository.field.FieldEntity;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class FieldDefinitionFactory {


    public List<FieldDefinition> createList(ProtocolEntity protocolEntity) {
        if (protocolEntity == null) return Collections.emptyList();

        return protocolEntity.getFieldEntityList().stream()
            .map(f -> new FixedDefinition(f.getName(), f.getLength()))
            .collect(Collectors.toList());
    }

    public Optional<FieldDefinition> create(FieldEntity fieldEntity) {
        if (Objects.isNull(fieldEntity)) return Optional.empty();

        switch (fieldEntity.getType()) {
            case FIXED:
                return Optional.of(new FixedDefinition(fieldEntity.getName(), fieldEntity.getLength()));
            default:
                //TODO add logger here
                throw new IllegalStateException("Unexpected value: " + fieldEntity.getType());
        }
    }
    public List<FieldDefinition> createList(List<FieldEntity> fieldEntityList) {
        if (Objects.isNull(fieldEntityList)) return Collections.emptyList();

        return fieldEntityList.stream()
            .map(f -> this.create(f).get())
            .collect(Collectors.toList());
    }

}
