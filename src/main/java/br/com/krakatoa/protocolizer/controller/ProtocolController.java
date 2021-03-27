package br.com.krakatoa.protocolizer.controller;

import br.com.krakatoa.protocolizer.form.ProtocolForm;
import br.com.krakatoa.protocolizer.repository.field.FieldDataProvider;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolEntity;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolDataProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/protocol")
public class ProtocolController {

    private final ProtocolDataProvider protocolDataProvider;
    private final FieldDataProvider fieldDataProvider;

    public ProtocolController(ProtocolDataProvider protocolDataProvider, FieldDataProvider fieldDataProvider) {
        this.protocolDataProvider = protocolDataProvider;
        this.fieldDataProvider = fieldDataProvider;
    }

    @PostMapping
    @ResponseBody
    @Transactional
    public ResponseEntity<Long> createProtocol(@RequestBody @Valid ProtocolForm protocolForm) {

        ProtocolEntity protocolEntity = protocolForm.convertToProtocol();

        //todo use json format in H2
        this.protocolDataProvider.save(protocolEntity);
        this.fieldDataProvider.saveAll(protocolEntity.getFieldEntityEntities());

        return ResponseEntity.ok(protocolEntity.getId());
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ProtocolEntity>> getAllProtocol() {
        List<ProtocolEntity> protocolEntityList = this.protocolDataProvider.findAll();
        if (protocolEntityList.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(protocolEntityList);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ProtocolEntity> getProtocolById(@PathVariable Long id) {
        Optional<ProtocolEntity> optProtocol = this.protocolDataProvider.findById(id);
        return optProtocol
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
