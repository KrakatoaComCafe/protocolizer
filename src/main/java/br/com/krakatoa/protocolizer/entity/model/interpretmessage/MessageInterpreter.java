package br.com.krakatoa.protocolizer.entity.model.interpretmessage;

import br.com.krakatoa.protocolizer.entity.model.interpretmessage.messagefields.MessageValues;
import br.com.krakatoa.protocolizer.entity.model.interpretmessage.protocol.ProtocolDefinition;

import java.util.Map;

public class MessageInterpreter {

    private final ProtocolDefinition protocolDefinition;
    private final String bitmap;
    private final String rawData;
    private final MessageValues messageValues;
    private final Map<String, String> fieldsWithNewValue;

    public MessageInterpreter(ProtocolDefinition protocolDefinition, String bitmap, String rawData, MessageValues messageValues, Map<String, String> fieldsWithNewValue) {
        this.protocolDefinition = protocolDefinition;
        this.bitmap = bitmap;
        this.rawData = rawData;
        this.messageValues = messageValues;
        this.fieldsWithNewValue = fieldsWithNewValue;
    }

    public String generateRawData() {
        Map<String, String> newFieldValues = this.messageValues.generateFieldsWithNewValue(this.fieldsWithNewValue);

        StringBuilder sb = new StringBuilder();
        messageValues.getFieldList()
            .forEach((key, value) -> sb.append(newFieldValues.getOrDefault(key, value)));

        return sb.toString();
    }
}
