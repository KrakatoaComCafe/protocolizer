package br.com.krakatoa.protocolizer.service;

import br.com.krakatoa.protocolizer.controller.dto.message.MessageDTO;
import br.com.krakatoa.protocolizer.form.MessageForm;
import br.com.krakatoa.protocolizer.repository.field.Field;
import br.com.krakatoa.protocolizer.repository.protocol.Protocol;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolDataProvider;

import java.util.List;
import java.util.stream.Collectors;

public class MessageService {

    private final ProtocolDataProvider protocolDataProvider;
    private final ConverterService converterService;

    public MessageService(ProtocolDataProvider protocolDataProvider, ConverterService converterService) {
        this.protocolDataProvider = protocolDataProvider;
        this.converterService = converterService;
    }

    public MessageDTO interpretMessage(MessageForm messageForm) {
        List<String> fieldList = this.converterService.hexToListOfBitmapPresent(messageForm.getBitmap());

        Protocol protocol = this.protocolDataProvider.findOneByNameAndVersion(messageForm.getProtocol(), messageForm.getVersion());
        List<Field> filteredFields = this.filterFieldDefinition(fieldList, protocol.getFields());

        return new MessageDTO(messageForm, filteredFields);
    }

    private List<Field> filterFieldDefinition(List<String> fieldList, List<Field> protocolFields) {
        return protocolFields.stream()
                .filter(f -> fieldList.contains(f.getName()))
                .collect(Collectors.toList());
    }
}
