package br.com.krakatoa.protocolizer.controller;

import br.com.krakatoa.protocolizer.repository.field.Field;
import br.com.krakatoa.protocolizer.repository.field.FieldDataProvider;
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

    @Autowired
    public FieldController(FieldDataProvider fieldDataProvider) {
        this.fieldDataProvider = fieldDataProvider;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Field>> getAllFields() {
        List<Field> fieldList = this.fieldDataProvider.findAll();

        if (fieldList.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(fieldList);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Field> getFieldById(@PathVariable Long id) {
        Optional<Field> optField = this.fieldDataProvider.findById(id);

        return optField
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
