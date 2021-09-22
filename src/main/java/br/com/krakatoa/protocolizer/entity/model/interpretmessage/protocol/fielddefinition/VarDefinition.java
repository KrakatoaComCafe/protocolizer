package br.com.krakatoa.protocolizer.entity.model.interpretmessage.protocol.fielddefinition;

import lombok.Getter;

@Getter
public class VarDefinition implements FieldDefinition {

    private final String name;
    private final int vli;
    private final int maxLength;
    private final int minLength;

    public VarDefinition(String name, int vli, int maxLength, int minLength) {
        this.name = name;
        this.vli = vli;
        this.maxLength = maxLength;
        this.minLength = minLength;
    }

    @Override
    public int getLength() {
        return this.vli;
    }
}
