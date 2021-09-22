package br.com.krakatoa.protocolizer.entity.response.entity;

import br.com.krakatoa.protocolizer.entity.response.entity.field.FieldEntityResponse;
import br.com.krakatoa.protocolizer.entity.response.entity.field.FieldEntityResponseFactory;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProtocolEntityResponseFactory {

    private final FieldEntityResponseFactory fieldEntityResponseFactory;

    @Autowired
    public ProtocolEntityResponseFactory(FieldEntityResponseFactory fieldEntityResponseFactory) {
        this.fieldEntityResponseFactory = fieldEntityResponseFactory;
    }

    public DefaultProtocolEntityResponse create(ProtocolEntity protocolEntity) {
        List<FieldEntityResponse> fieldEntityResponseList = protocolEntity.getFieldEntityList()
            .stream()
            .map(this.fieldEntityResponseFactory::create)
            .collect(Collectors.toList());

        return new DefaultProtocolEntityResponse(
            protocolEntity.getId(),
            protocolEntity.getName(),
            protocolEntity.getVersion(),
            protocolEntity.getEncoding(),
            fieldEntityResponseList);
    }
}
