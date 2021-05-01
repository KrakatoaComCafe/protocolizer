package br.com.krakatoa.protocolizer.model.interpretmessage.protocol;

import br.com.krakatoa.protocolizer.model.interpretmessage.protocol.fielddefinition.FieldDefinition;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolEntity;

import java.util.Collections;
import java.util.List;

public class ProtocolFactory {
    //TODO take care of the NullPointerException when protocolEntity is null
    public Protocol create(ProtocolEntity protocolEntity, List<FieldDefinition> fieldDefinitionList) {
        if (fieldDefinitionList == null) fieldDefinitionList = Collections.emptyList();

        return new Protocol(protocolEntity.getName(), protocolEntity.getVersion(), fieldDefinitionList);
    }
}
