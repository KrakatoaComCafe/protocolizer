package br.com.krakatoa.protocolizer.service;

import br.com.krakatoa.protocolizer.controller.dto.message.MessageDTO;
import br.com.krakatoa.protocolizer.form.MessageForm;
import br.com.krakatoa.protocolizer.repository.field.FieldEntity;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolEntity;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolDataProvider;

import java.util.List;
import java.util.stream.Collectors;

public class InterpretMessageService {

    private final ProtocolDataProvider protocolDataProvider;
    private final ConverterService converterService;

    public InterpretMessageService(ProtocolDataProvider protocolDataProvider, ConverterService converterService) {
        this.protocolDataProvider = protocolDataProvider;
        this.converterService = converterService;
    }

    public MessageDTO interpret(MessageForm messageForm) {
        List<FieldEntity> filteredFieldEntityEntities = this.getFilteredFields(messageForm);

        return new MessageDTO(messageForm, filteredFieldEntityEntities);
    }

    public List<FieldEntity> getFilteredFields(MessageForm messageForm) {
        return this.getFilteredFields(messageForm.getBitmap(), messageForm.getProtocol(), messageForm.getVersion());
    }

    public List<FieldEntity> getFilteredFields(String bitmap, String protocolName, String version) {
        List<String> fieldsActivated = this.converterService.hexToListOfBitmapPresent(bitmap);

        ProtocolEntity protocolEntity = this.protocolDataProvider.findOneByNameAndVersion(protocolName, version);
        return this.filterFieldDefinition(fieldsActivated, protocolEntity.getFieldEntityList());
    } 

    private List<FieldEntity> filterFieldDefinition(List<String> fieldList, List<FieldEntity> protocolFieldEntityEntities) {
        return protocolFieldEntityEntities.stream()
                .filter(f -> fieldList.contains(f.getName()))
                .collect(Collectors.toList());
    }
}
