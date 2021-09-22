package br.com.krakatoa.protocolizer.entity.form;

import br.com.krakatoa.protocolizer.format.FieldType;
import br.com.krakatoa.protocolizer.repository.field.FieldEntity;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FieldForm {

    private final String name;
    private final FieldType type;
    private final int length;
    private final int maxLength;
    private final int minLength;

    public FieldEntity convertToField(ProtocolEntity protocolEntity) {
        return new FieldEntity(this.name, this.type, this.length, this.maxLength, this.minLength, protocolEntity);
    }
}
