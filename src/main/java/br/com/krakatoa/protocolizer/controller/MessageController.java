package br.com.krakatoa.protocolizer.controller;

import br.com.krakatoa.protocolizer.entity.model.message.Message;
import br.com.krakatoa.protocolizer.entity.response.message.MessageResponse;
import br.com.krakatoa.protocolizer.entity.form.MessageChangeForm;
import br.com.krakatoa.protocolizer.entity.form.MessageForm;
import br.com.krakatoa.protocolizer.service.InterpretMessageService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public MessageController(InterpretMessageService interpretMessageService) {
        this.interpretMessageService = interpretMessageService;
    }

    @PostMapping("/interpret")
    @ResponseBody
    public ResponseEntity<MessageResponse> interpretMessage(@RequestBody @Validated MessageForm messageForm) {
        Message message = new Message.Factory().create(messageForm);
        MessageResponse messageResponse = this.interpretMessageService.interpret(message);

        return ResponseEntity.ok(messageResponse);
    }

    @PostMapping("/replace")
    @ResponseBody
    public ResponseEntity<String> replaceMessage(@RequestBody MessageChangeForm messageChangeForm) {
        String response = this.interpretMessageService.replace(messageChangeForm);

        return ResponseEntity.ok(response);
    }
}
