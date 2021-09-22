package br.com.krakatoa.protocolizer.entity.model.interpretmessage;

import br.com.krakatoa.protocolizer.entity.form.MessageChangeForm;
import br.com.krakatoa.protocolizer.entity.model.interpretmessage.messagefields.MessageValues;
import br.com.krakatoa.protocolizer.entity.model.interpretmessage.messagefields.MessageValuesFactory;
import br.com.krakatoa.protocolizer.entity.model.interpretmessage.protocol.ProtocolDefinition;
import br.com.krakatoa.protocolizer.entity.model.interpretmessage.protocol.ProtocolDefinitionFactory;
import br.com.krakatoa.protocolizer.entity.model.interpretmessage.protocol.fielddefinition.FieldDefinition;
import br.com.krakatoa.protocolizer.entity.model.interpretmessage.protocol.fielddefinition.FieldDefinitionFactory;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class MessageInterpretFactory {

    private final Logger LOGGER = LoggerFactory.getLogger(MessageInterpretFactory.class);
    private final FieldDefinitionFactory fieldDefinitionFactory;
    private final ProtocolDefinitionFactory protocolDefinitionFactory;
    private final MessageValuesFactory messageValuesFactory;

    public MessageInterpretFactory() {
        this(new FieldDefinitionFactory(), new ProtocolDefinitionFactory(), new MessageValuesFactory());
    }

    MessageInterpretFactory(FieldDefinitionFactory fieldDefinitionFactory, ProtocolDefinitionFactory protocolDefinitionFactory, MessageValuesFactory messageValuesFactory) {
        this.fieldDefinitionFactory = fieldDefinitionFactory;
        this.protocolDefinitionFactory = protocolDefinitionFactory;
        this.messageValuesFactory = messageValuesFactory;
    }

    public MessageInterpreter create(ProtocolEntity protocolEntity, MessageChangeForm messageChangeForm) {
        if(!this.validate(protocolEntity, messageChangeForm)) throw new RuntimeException("An error occur creating MessageInterpreter.");

        List<FieldDefinition> fieldDefinitionList = this.fieldDefinitionFactory.createList(protocolEntity);
        ProtocolDefinition protocolDefinition = this.protocolDefinitionFactory.create(protocolEntity, fieldDefinitionList);

        MessageValues messageValues = this.messageValuesFactory.create(messageChangeForm, fieldDefinitionList);

        return new MessageInterpreter(protocolDefinition, messageChangeForm.getBitmap(), messageChangeForm.getRawData(), messageValues, messageChangeForm.getFields());
    }

    private boolean validate(ProtocolEntity protocolEntity, MessageChangeForm messageChangeForm) {
        if(Objects.isNull(messageChangeForm)) {
            LOGGER.error("MessageChangeForm has null value");
            return false;
        }

        if(Objects.isNull(protocolEntity)) {
            LOGGER.error("ProtocolEntity has null value");
            return false;
        }

        return true;
    }
}
