package br.com.krakatoa.protocolizer.controller;

import br.com.krakatoa.protocolizer.controller.dto.ProtocolMessageDto;
import br.com.krakatoa.protocolizer.form.MessageForm;
import br.com.krakatoa.protocolizer.form.ProtocolForm;
import br.com.krakatoa.protocolizer.model.Protocol;
import br.com.krakatoa.protocolizer.model.ProtocolMessage;
import br.com.krakatoa.protocolizer.repository.FieldRepository;
import br.com.krakatoa.protocolizer.repository.ProtocolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MessageController {

    @Autowired
    private ProtocolRepository protocolRepository;

    @Autowired
    private FieldRepository fieldRepository;

    @PutMapping("/message")
    @ResponseBody
    public ResponseEntity<ProtocolMessageDto> generateMessage(@RequestBody @Valid MessageForm messageForm) {

        ProtocolMessage protocolMessage = messageForm.toMessage();
        protocolMessage.updateRaw();

        return ResponseEntity.ok(protocolMessage.toDto());
    }

    @PutMapping("/protocol")
    @ResponseBody
    @Transactional
    public ResponseEntity<Long> createProtocol(@RequestBody @Valid ProtocolForm protocolForm) {

        Protocol protocol = protocolForm.convertToProtocol();

        //todo use json format in H2
        this.protocolRepository.save(protocol);
        this.fieldRepository.saveAll(protocol.getFields());

        return ResponseEntity.ok(protocol.getId());
    }
}
