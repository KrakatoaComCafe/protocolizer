package br.com.krakatoa.protocolizer.entity.response.entity.field;

import br.com.krakatoa.protocolizer.format.FieldType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VarEntityResponse implements FieldEntityResponse {

    private final Long id;
    private final String name;
    private final FieldType type;
    private final int vli;
    private final int maxLength;
    private final int minLength;
    private final Long protocolId;
}
