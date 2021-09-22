package br.com.krakatoa.protocolizer.entity.response.definition;

import br.com.krakatoa.protocolizer.format.FieldType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class FixedDefinitionResponse implements FieldDefinitionResponse {

    private final Long id;
    private final String name;
    private final FieldType fixed;
    private final int length;
    private final Long protocolId;
}
