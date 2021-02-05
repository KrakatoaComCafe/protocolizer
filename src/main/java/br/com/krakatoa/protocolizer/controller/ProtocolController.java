package br.com.krakatoa.protocolizer.controller;

import br.com.krakatoa.protocolizer.form.ProtocolForm;
import br.com.krakatoa.protocolizer.repository.field.FieldDataProvider;
import br.com.krakatoa.protocolizer.repository.protocol.Protocol;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolDataProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/protocol")
public class ProtocolController {

    private final ProtocolDataProvider protocolDataProvider;
    private final FieldDataProvider fieldDataProvider;

    public ProtocolController(ProtocolDataProvider protocolDataProvider, FieldDataProvider fieldDataProvider) {
        this.protocolDataProvider = protocolDataProvider;
        this.fieldDataProvider = fieldDataProvider;
    }

    @PutMapping
    @ResponseBody
    @Transactional
    public ResponseEntity<Long> createProtocol(@RequestBody @Valid ProtocolForm protocolForm) {

        Protocol protocol = protocolForm.convertToProtocol();

        //todo use json format in H2
        this.protocolDataProvider.save(protocol);
        this.fieldDataProvider.saveAll(protocol.getFields());

        return ResponseEntity.ok(protocol.getId());
    }
}
