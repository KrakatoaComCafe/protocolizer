package br.com.krakatoa.protocolizer.model.interpretmessage.protocol.fielddefinition;

import lombok.Getter;

@Getter
public class FieldDefinition {

    private final String name;
    private final int length;

    public FieldDefinition(String name, int length) {
        this.name = name;
        this.length = length;
    }
}
