package br.com.krakatoa.protocolizer.form;

import br.com.krakatoa.protocolizer.repository.field.Field;
import br.com.krakatoa.protocolizer.repository.protocol.Protocol;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FieldForm {

    private final String name;
    private final int length;

    public Field convertToField(Protocol protocol) {
        return new Field(this.name, this.length, protocol);
    }
}
