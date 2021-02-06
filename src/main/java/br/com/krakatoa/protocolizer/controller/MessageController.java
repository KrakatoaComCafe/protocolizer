package br.com.krakatoa.protocolizer.controller;

import br.com.krakatoa.protocolizer.controller.dto.message.MessageDTO;
import br.com.krakatoa.protocolizer.form.MessageForm;
import br.com.krakatoa.protocolizer.service.InterpretMessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final InterpretMessageService interpretMessageService;

    public MessageController(InterpretMessageService interpretMessageService) {
        this.interpretMessageService = interpretMessageService;
    }

    @PostMapping("/interpret")
    @ResponseBody
    public ResponseEntity<MessageDTO> interpretMessage(@RequestBody @Validated MessageForm messageForm) {
        MessageDTO messageDTO = this.interpretMessageService.interpret(messageForm);
        return ResponseEntity.ok(messageDTO);
    }
}
