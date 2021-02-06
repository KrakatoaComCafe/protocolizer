package br.com.krakatoa.protocolizer.controller;

import br.com.krakatoa.protocolizer.form.ProtocolForm;
import br.com.krakatoa.protocolizer.repository.field.FieldDataProvider;
import br.com.krakatoa.protocolizer.repository.protocol.Protocol;
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

        Protocol protocol = protocolForm.convertToProtocol();

        //todo use json format in H2
        this.protocolDataProvider.save(protocol);
        this.fieldDataProvider.saveAll(protocol.getFields());

        return ResponseEntity.ok(protocol.getId());
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Protocol>> getAllProtocol() {
        List<Protocol> protocolList = this.protocolDataProvider.findAll();
        if (protocolList.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(protocolList);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Protocol> getProtocolById(@PathVariable Long id) {
        Optional<Protocol> optProtocol = this.protocolDataProvider.findById(id);
        return optProtocol
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
