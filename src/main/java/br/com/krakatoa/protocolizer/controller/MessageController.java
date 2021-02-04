package br.com.krakatoa.protocolizer.controller;

import br.com.krakatoa.protocolizer.controller.dto.message.MessageDTO;
import br.com.krakatoa.protocolizer.form.MessageForm;
import br.com.krakatoa.protocolizer.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PutMapping("/message/interpret")
    @ResponseBody
    public ResponseEntity<MessageDTO> interpretMessage(@RequestBody @Validated MessageForm messageForm) {
        MessageDTO messageDTO = this.messageService.interpretMessage(messageForm);
        return ResponseEntity.ok(messageDTO);
    }
}
