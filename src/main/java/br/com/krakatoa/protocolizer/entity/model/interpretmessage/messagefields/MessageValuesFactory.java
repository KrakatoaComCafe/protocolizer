package br.com.krakatoa.protocolizer.entity.model.interpretmessage.messagefields;

import br.com.krakatoa.protocolizer.entity.form.MessageChangeForm;
import br.com.krakatoa.protocolizer.entity.model.interpretmessage.protocol.fielddefinition.FieldDefinition;
import br.com.krakatoa.protocolizer.service.ConverterAction;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MessageValuesFactory {

    private final ConverterAction converterAction;

    public MessageValuesFactory() {
        this.converterAction = new ConverterAction();
    }

    public MessageValues create(MessageChangeForm messageChangeForm, List<FieldDefinition> fieldDefinitionList) {

        List<FieldDefinition> activatedFieldDefinitionList = this.getFilteredFields(messageChangeForm.getBitmap(), fieldDefinitionList);

        int lastPos = 0;
        Map<String, String> fields = new TreeMap<>();

        String rawData = messageChangeForm.getRawData();
        for (FieldDefinition fieldDefinition : activatedFieldDefinitionList) {
            fields.put(fieldDefinition.getName(), rawData.substring(lastPos, lastPos + fieldDefinition.getLength()));
            lastPos += fieldDefinition.getLength();
        }
        return new MessageValues(fields);
    }

    private List<FieldDefinition> getFilteredFields(String bitmap, List<FieldDefinition> fieldDefinitionList) {
        List<String> fieldActivatedList = this.converterAction.hexToListOfBitmapPresent(bitmap);
        return this.filterFieldDefinition(fieldActivatedList, fieldDefinitionList);

    }

    private List<FieldDefinition> filterFieldDefinition(List<String> fieldActivatedList, List<FieldDefinition> protocolFieldDefinitions) {
        return protocolFieldDefinitions.stream()
            .filter(f -> fieldActivatedList.contains(f.getName()))
            .collect(Collectors.toList());
    }
}
