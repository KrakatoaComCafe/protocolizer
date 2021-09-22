package br.com.krakatoa.protocolizer.service;

import br.com.krakatoa.protocolizer.entity.response.definition.FieldDefinitionResponse;
import br.com.krakatoa.protocolizer.entity.response.definition.FieldDefinitionResponseFactory;
import br.com.krakatoa.protocolizer.repository.field.FieldDataProvider;
import br.com.krakatoa.protocolizer.repository.field.FieldEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FieldService {

    private final FieldDataProvider fieldDataProvider;
    private final FieldDefinitionResponseFactory fieldDefinitionResponseFactory;

    @Autowired
    public FieldService(FieldDataProvider fieldDataProvider,
                        FieldDefinitionResponseFactory fieldDefinitionResponseFactory) {
        this.fieldDataProvider = fieldDataProvider;
        this.fieldDefinitionResponseFactory = fieldDefinitionResponseFactory;
    }

    public List<FieldDefinitionResponse> getAllFields() {
        List<FieldEntity> fieldEntityList = this.fieldDataProvider.findAll();

        return fieldEntityList.stream()
            .map(this.fieldDefinitionResponseFactory::create)
            .collect(Collectors.toList());
    }

    public FieldDefinitionResponse getField(Long id) {
        Optional<FieldEntity> optFieldEntity = this.fieldDataProvider.findById(id);

        return optFieldEntity
            .map(this.fieldDefinitionResponseFactory::create)
            .orElse(null);
    }
}
