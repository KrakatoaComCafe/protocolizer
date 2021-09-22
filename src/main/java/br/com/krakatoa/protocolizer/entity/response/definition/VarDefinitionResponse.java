package br.com.krakatoa.protocolizer.entity.response.definition;

import br.com.krakatoa.protocolizer.format.FieldType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class VarDefinitionResponse implements FieldDefinitionResponse {

    private final Long id;
    private final String name;
    private final FieldType fieldType;
    private final int vli;
    private final Long protocolId;
}
