package br.com.krakatoa.protocolizer.controller;

import br.com.krakatoa.protocolizer.entity.response.definition.FieldDefinitionResponse;
import br.com.krakatoa.protocolizer.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/field")
public class FieldController {

    private final FieldService fieldService;

    @Autowired
    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<FieldDefinitionResponse>> getAllFields() {
        List<FieldDefinitionResponse> fieldDefinitionResponseList = this.fieldService.getAllFields();

        if (fieldDefinitionResponseList.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(fieldDefinitionResponseList);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<FieldDefinitionResponse> getFieldById(@PathVariable Long id) {
        FieldDefinitionResponse fieldDefinitionResponse = this.fieldService.getField(id);

        return ResponseEntity.ok(fieldDefinitionResponse);
    }
}
