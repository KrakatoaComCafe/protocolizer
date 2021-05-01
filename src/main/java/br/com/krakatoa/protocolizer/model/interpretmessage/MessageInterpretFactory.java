package br.com.krakatoa.protocolizer.model.interpretmessage;

import br.com.krakatoa.protocolizer.form.MessageChangeForm;
import br.com.krakatoa.protocolizer.model.interpretmessage.messagefields.MessageValues;
import br.com.krakatoa.protocolizer.model.interpretmessage.messagefields.MessageValuesFactory;
import br.com.krakatoa.protocolizer.model.interpretmessage.protocol.Protocol;
import br.com.krakatoa.protocolizer.model.interpretmessage.protocol.ProtocolFactory;
import br.com.krakatoa.protocolizer.model.interpretmessage.protocol.fielddefinition.FieldDefinition;
import br.com.krakatoa.protocolizer.model.interpretmessage.protocol.fielddefinition.FieldDefinitionFactory;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

public class MessageInterpretFactory {

    private final Logger LOGGER = LoggerFactory.getLogger(MessageInterpretFactory.class);
    private final FieldDefinitionFactory fieldDefinitionFactory;
    private final ProtocolFactory protocolFactory;
    private final MessageValuesFactory messageValuesFactory;

    public MessageInterpretFactory() {
        this(new FieldDefinitionFactory(), new ProtocolFactory(), new MessageValuesFactory());
    }

    MessageInterpretFactory(FieldDefinitionFactory fieldDefinitionFactory, ProtocolFactory protocolFactory, MessageValuesFactory messageValuesFactory) {
        this.fieldDefinitionFactory = fieldDefinitionFactory;
        this.protocolFactory = protocolFactory;
        this.messageValuesFactory = messageValuesFactory;
    }

    public MessageInterpreter create(ProtocolEntity protocolEntity, MessageChangeForm messageChangeForm) {
        if(!this.validate(protocolEntity, messageChangeForm)) throw new RuntimeException("An error occur creating MessageInterpreter.");

        List<FieldDefinition> fieldDefinitionList = this.fieldDefinitionFactory.createList(protocolEntity);
        Protocol protocol = this.protocolFactory.create(protocolEntity, fieldDefinitionList);

        MessageValues messageValues = this.messageValuesFactory.create(messageChangeForm, fieldDefinitionList);

        return new MessageInterpreter(protocol, messageChangeForm.getBitmap(), messageChangeForm.getRawData(), messageValues, messageChangeForm.getFields());
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
