package br.com.krakatoa.protocolizer.model.interpretmessage;

import br.com.krakatoa.protocolizer.model.interpretmessage.messagefields.MessageValues;
import br.com.krakatoa.protocolizer.model.interpretmessage.protocol.Protocol;

import java.util.Map;

public class MessageInterpreter {

    private final Protocol protocol;
    private final String bitmap;
    private final String rawData;
    private MessageValues messageValues;
    private Map<String, String> fieldsWithNewValue;

    public MessageInterpreter(Protocol protocol, String bitmap, String rawData, MessageValues messageValues, Map<String, String> fieldsWithNewValue) {
        this.protocol = protocol;
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
