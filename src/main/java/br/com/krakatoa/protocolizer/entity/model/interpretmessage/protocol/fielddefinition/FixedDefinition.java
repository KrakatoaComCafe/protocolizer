package br.com.krakatoa.protocolizer.entity.model.interpretmessage.protocol.fielddefinition;

import lombok.Getter;

@Getter
public class FixedDefinition implements FieldDefinition {

    private final String name;
    private final int length;

    public FixedDefinition(String name, int length) {
        this.name = name;
        this.length = length;
    }
}
