package br.com.krakatoa.protocolizer.controller;

import br.com.krakatoa.protocolizer.controller.dto.message.MessageDTO;
import br.com.krakatoa.protocolizer.form.MessageChangeForm;
import br.com.krakatoa.protocolizer.form.MessageForm;
import br.com.krakatoa.protocolizer.model.interpretmessage.MessageInterpretFactory;
import br.com.krakatoa.protocolizer.model.interpretmessage.MessageInterpreter;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolEntity;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolDataProvider;
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

    private final ProtocolDataProvider protocolDataProvider;
    private final InterpretMessageService interpretMessageService;
    private final MessageInterpretFactory interpretMessageFactory;

    @Autowired
    public MessageController(ProtocolDataProvider protocolDataProvider, InterpretMessageService interpretMessageService) {
        this.protocolDataProvider = protocolDataProvider;
        this.interpretMessageService = interpretMessageService;
        this.interpretMessageFactory = new MessageInterpretFactory();
    }

    @PostMapping("/interpret")
    @ResponseBody
    public ResponseEntity<MessageDTO> interpretMessage(@RequestBody @Validated MessageForm messageForm) {
        MessageDTO messageDTO = this.interpretMessageService.interpret(messageForm);
        return ResponseEntity.ok(messageDTO);
    }

    @PostMapping("/replace")
    @ResponseBody
    public ResponseEntity<String> replaceMessage(@RequestBody MessageChangeForm messageChangeForm) {
        ProtocolEntity protocolEntity = this.protocolDataProvider.findOneByNameAndVersion(messageChangeForm.getProtocolName(), messageChangeForm.getProtocolVersion());

        MessageInterpreter messageInterpreter = this.interpretMessageFactory.create(protocolEntity, messageChangeForm);
        String response = messageInterpreter.generateRawData();

        return ResponseEntity.ok(response);
    }
}
