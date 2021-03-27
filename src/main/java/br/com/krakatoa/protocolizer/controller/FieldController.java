package br.com.krakatoa.protocolizer.controller;

import br.com.krakatoa.protocolizer.model.interpretmessage.protocol.fielddefinition.FieldDefinition;
import br.com.krakatoa.protocolizer.model.interpretmessage.protocol.fielddefinition.FieldDefinitionFactory;
import br.com.krakatoa.protocolizer.repository.field.FieldDataProvider;
import br.com.krakatoa.protocolizer.repository.field.FieldEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/field")
public class FieldController {

    private final FieldDataProvider fieldDataProvider;
    private final FieldDefinitionFactory fieldDefinitionFactory;

    @Autowired
    public FieldController(FieldDataProvider fieldDataProvider) {
        this.fieldDefinitionFactory = new FieldDefinitionFactory();
        this.fieldDataProvider = fieldDataProvider;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<FieldDefinition>> getAllFields() {
        List<FieldEntity> fieldEntityList = this.fieldDataProvider.findAll();
        List<FieldDefinition> fieldDefinitionList = this.fieldDefinitionFactory.createList(fieldEntityList);

        if (fieldDefinitionList.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(fieldDefinitionList);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<FieldDefinition> getFieldById(@PathVariable Long id) {
        Optional<FieldEntity> optFieldEntity = this.fieldDataProvider.findById(id);
        if (optFieldEntity.isEmpty()) return ResponseEntity.notFound().build();

        Optional<FieldDefinition> optFieldDefinition = this.fieldDefinitionFactory.create(optFieldEntity.get());
        return optFieldDefinition
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
