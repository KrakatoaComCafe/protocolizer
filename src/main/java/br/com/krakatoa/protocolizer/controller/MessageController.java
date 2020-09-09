package br.com.krakatoa.protocolizer.controller;

import br.com.krakatoa.protocolizer.model.ProtocolMessage;
import br.com.krakatoa.protocolizer.controller.dto.ProtocolMessageDto;
import br.com.krakatoa.protocolizer.form.MessageForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MessageController {

    @PutMapping("/message")
    @ResponseBody
    public ResponseEntity<ProtocolMessageDto> generateMessage(@RequestBody @Valid MessageForm messageForm) {

        ProtocolMessage protocolMessage = messageForm.toMessage();
        protocolMessage.updateRaw();

        return ResponseEntity.ok(protocolMessage.toDto());
    }
}
