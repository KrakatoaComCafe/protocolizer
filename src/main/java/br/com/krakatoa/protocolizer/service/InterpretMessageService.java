package br.com.krakatoa.protocolizer.service;

import br.com.krakatoa.protocolizer.entity.form.MessageChangeForm;
import br.com.krakatoa.protocolizer.entity.form.MessageForm;
import br.com.krakatoa.protocolizer.entity.model.field.Field;
import br.com.krakatoa.protocolizer.entity.model.field.FieldFactory;
import br.com.krakatoa.protocolizer.entity.model.interpretmessage.MessageInterpretFactory;
import br.com.krakatoa.protocolizer.entity.model.interpretmessage.MessageInterpreter;
import br.com.krakatoa.protocolizer.entity.model.message.Message;
import br.com.krakatoa.protocolizer.entity.response.message.MessageResponse;
import br.com.krakatoa.protocolizer.repository.field.FieldEntity;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolDataProvider;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterpretMessageService {

    private final ProtocolDataProvider protocolDataProvider;
    private final ConverterAction converterAction;
    private final FieldAction fieldAction;
    private final FieldFactory fieldFactory;
    private final MessageInterpretFactory messageInterpretFactory;

    @Autowired
    public InterpretMessageService(ProtocolDataProvider protocolDataProvider, ConverterAction converterAction,
                                   FieldAction fieldAction, FieldFactory fieldFactory,
                                   MessageInterpretFactory messageInterpretFactory) {
        this.protocolDataProvider = protocolDataProvider;
        this.converterAction = converterAction;
        this.fieldAction = fieldAction;
        this.fieldFactory = fieldFactory;
        this.messageInterpretFactory = messageInterpretFactory;
    }

    public MessageResponse interpret(Message message) {
        List<String> fieldsActivated = this.converterAction.hexToListOfBitmapPresent(message.getBitmap());
        List<FieldEntity> filteredFields = this.fieldAction
            .getFilteredFields(message.getProtocol(), message.getVersion(), fieldsActivated);

        List<Field> fieldList = this.fieldFactory.createList(filteredFields);

        return new MessageResponse.Factory().create(message, fieldList);
    }

    public String replace(MessageChangeForm messageChangeForm) {
        ProtocolEntity protocolEntity = this.protocolDataProvider
            .findOneByNameAndVersion(messageChangeForm.getProtocolName(), messageChangeForm.getProtocolVersion());

        MessageInterpreter messageInterpreter = this.messageInterpretFactory.create(protocolEntity, messageChangeForm);
        return messageInterpreter.generateRawData();
    }
}
