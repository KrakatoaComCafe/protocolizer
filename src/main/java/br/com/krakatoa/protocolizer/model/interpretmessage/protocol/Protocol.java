package br.com.krakatoa.protocolizer.model.interpretmessage.protocol;

import br.com.krakatoa.protocolizer.model.interpretmessage.protocol.fielddefinition.FieldDefinition;
import lombok.Getter;

import java.util.List;

@Getter
public class Protocol {

    private final String name;
    private final String version;
    private final List<FieldDefinition> fieldDefinitionList;

    public Protocol(String name, String version, List<FieldDefinition> fieldDefinitionList) {
        this.name = name;
        this.version = version;
        this.fieldDefinitionList = fieldDefinitionList;
    }
}