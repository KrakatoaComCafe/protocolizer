package br.com.krakatoa.protocolizer.form;

import br.com.krakatoa.protocolizer.format.FieldType;
import br.com.krakatoa.protocolizer.repository.field.FieldEntity;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FieldForm {

    private final String name;
    private final FieldType type;
    private final int length;

    public FieldEntity convertToField(ProtocolEntity protocolEntity) {
        return new FieldEntity(this.name, this.type, this.length, protocolEntity);
    }
}
