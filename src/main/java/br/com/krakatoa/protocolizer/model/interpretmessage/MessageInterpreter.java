package br.com.krakatoa.protocolizer.model.interpretmessage;

import br.com.krakatoa.protocolizer.model.interpretmessage.messagefields.MessageValues;
import br.com.krakatoa.protocolizer.model.interpretmessage.protocol.Protocol;

import java.util.Map;

public class MessageInterpreter {

    private final Protocol protocol;
    private final String bitmap;
    private final String rawData;
    private MessageValues fieldList;
    private Map<String, String> fieldsWithNewValue;

    public MessageInterpreter(Protocol protocol, String bitmap, String rawData, MessageValues messageValues, Map<String, String> fieldsWithNewValue) {
        this.protocol = protocol;
        this.bitmap = bitmap;
        this.rawData = rawData;
        this.fieldList = messageValues;
        this.fieldsWithNewValue = fieldsWithNewValue;
    }

    public String generateRawData() {
        Map<String, String> newFieldValues = this.fieldList.generateFieldsWithNewValue(this.fieldsWithNewValue);

        StringBuilder sb = new StringBuilder();
        newFieldValues.forEach((key, value) -> sb.append(value));

        return sb.toString();
    }
}
