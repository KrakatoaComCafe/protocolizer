package br.com.krakatoa.protocolizer.model.interpretmessage.messagefields;

import br.com.krakatoa.protocolizer.form.MessageChangeForm;
import br.com.krakatoa.protocolizer.model.interpretmessage.protocol.fielddefinition.FieldDefinition;
import br.com.krakatoa.protocolizer.model.interpretmessage.protocol.fielddefinition.FixedDefinition;
import br.com.krakatoa.protocolizer.service.ConverterService;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MessageValuesFactory {

    private final ConverterService converterService;

    public MessageValuesFactory() {
        this.converterService = new ConverterService();
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
        List<String> fieldActivatedList = this.converterService.hexToListOfBitmapPresent(bitmap);
        return this.filterFieldDefinition(fieldActivatedList, fieldDefinitionList);

    }

    private List<FieldDefinition> filterFieldDefinition(List<String> fieldActivatedList, List<FieldDefinition> protocolFieldDefinitions) {
        return protocolFieldDefinitions.stream()
            .filter(f -> fieldActivatedList.contains(f.getName()))
            .collect(Collectors.toList());
    }
}
