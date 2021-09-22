package br.com.krakatoa.protocolizer.entity.response.entity;

import br.com.krakatoa.protocolizer.entity.response.entity.field.FieldEntityResponse;
import br.com.krakatoa.protocolizer.format.encoding.Encoding;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DefaultProtocolEntityResponse implements ProtocolEntityResponse {

    private final Long id;
    private final String name;
    private final String version;
    private final Encoding encoding;
    private final List<FieldEntityResponse> fieldEntityResponseList;

}
