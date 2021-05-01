package br.com.krakatoa.protocolizer.model.interpretmessage.messagefields;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class MessageValues {

    @Getter
    private final Map<String, String> fieldList;

    public MessageValues(Map<String, String> fieldList) {
        this.fieldList = fieldList;
    }

    public Map<String, String> generateFieldsWithNewValue(Map<String, String> fieldsWithNewValue) {
        Map<String, String> newFieldValues = new TreeMap<>();

        fieldsWithNewValue.forEach((key, value) -> {
            if (this.fieldList.containsKey(key)) {
                newFieldValues.put(key, value);
            }
        });

        return Collections.unmodifiableMap(newFieldValues);
    }
}
