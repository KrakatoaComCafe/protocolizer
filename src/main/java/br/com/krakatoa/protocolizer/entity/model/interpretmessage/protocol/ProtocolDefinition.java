package br.com.krakatoa.protocolizer.entity.model.interpretmessage.protocol;

import br.com.krakatoa.protocolizer.entity.model.interpretmessage.protocol.fielddefinition.FieldDefinition;
import lombok.Getter;

import java.util.List;

@Getter
public class ProtocolDefinition {

    private final String name;
    private final String version;
    private final List<FieldDefinition> fieldDefinitionList;

    public ProtocolDefinition(String name, String version, List<FieldDefinition> fieldDefinitionList) {
        this.name = name;
        this.version = version;
        this.fieldDefinitionList = fieldDefinitionList;
    }
}