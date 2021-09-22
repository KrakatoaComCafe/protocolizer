package br.com.krakatoa.protocolizer.service;

import br.com.krakatoa.protocolizer.repository.field.FieldEntity;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolDataProvider;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FieldAction {

    private final ProtocolDataProvider protocolDataProvider;

    @Autowired
    public FieldAction(ProtocolDataProvider protocolDataProvider) {
        this.protocolDataProvider = protocolDataProvider;
    }

    public List<FieldEntity> getFilteredFields(String protocolName, String version, List<String> fieldsActivated) {
        ProtocolEntity protocolEntity = this.protocolDataProvider.findOneByNameAndVersion(protocolName, version);

        return protocolEntity.getFieldEntityList().stream()
            .filter(f -> fieldsActivated.contains(f.getName()))
            .collect(Collectors.toList());
    }
}
