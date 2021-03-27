package br.com.krakatoa.protocolizer.model.interpretmessage;

import br.com.krakatoa.protocolizer.form.MessageChangeForm;
import br.com.krakatoa.protocolizer.model.interpretmessage.messagefields.MessageValues;
import br.com.krakatoa.protocolizer.model.interpretmessage.messagefields.MessageValuesFactory;
import br.com.krakatoa.protocolizer.model.interpretmessage.protocol.Protocol;
import br.com.krakatoa.protocolizer.model.interpretmessage.protocol.ProtocolFactory;
import br.com.krakatoa.protocolizer.model.interpretmessage.protocol.fielddefinition.FieldDefinition;
import br.com.krakatoa.protocolizer.model.interpretmessage.protocol.fielddefinition.FieldDefinitionFactory;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolEntity;
import br.com.krakatoa.protocolizer.service.ConverterService;

import java.util.List;

public class MessageInterpretFactory {

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
        List<FieldDefinition> fieldDefinitionList = this.fieldDefinitionFactory.createList(protocolEntity);
        Protocol protocol = this.protocolFactory.create(protocolEntity, fieldDefinitionList);

        MessageValues messageValues = this.messageValuesFactory.create(messageChangeForm, fieldDefinitionList);

        return new MessageInterpreter(protocol, messageChangeForm.getBitmap(), messageChangeForm.getRawData(), messageValues, messageChangeForm.getFields());
    }
}
