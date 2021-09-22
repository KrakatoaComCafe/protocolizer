package br.com.krakatoa.protocolizer.entity.model.interpretmessage.protocol;

import br.com.krakatoa.protocolizer.entity.model.interpretmessage.protocol.fielddefinition.FieldDefinition;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolEntity;

import java.util.Collections;
import java.util.List;

public class ProtocolDefinitionFactory {
    //TODO take care of the NullPointerException when protocolEntity is null
    public ProtocolDefinition create(ProtocolEntity protocolEntity, List<FieldDefinition> fieldDefinitionList) {
        if (fieldDefinitionList == null) fieldDefinitionList = Collections.emptyList();

        return new ProtocolDefinition(protocolEntity.getName(), protocolEntity.getVersion(), fieldDefinitionList);
    }

}
