package br.com.krakatoa.protocolizer.controller;

import br.com.krakatoa.protocolizer.entity.response.entity.ProtocolEntityResponse;
import br.com.krakatoa.protocolizer.entity.form.ProtocolForm;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolEntity;
import br.com.krakatoa.protocolizer.service.ProtocolService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final ProtocolService protocolService;

    @Autowired
    public ProtocolController(ProtocolService protocolService) {
        this.protocolService = protocolService;
    }

    @PostMapping
    @ResponseBody
    @Transactional
    public ResponseEntity<Long> createProtocol(@RequestBody @Valid ProtocolForm protocolForm) {
        ProtocolEntity protocolEntity = this.protocolService.save(protocolForm);

        return ResponseEntity.ok(protocolEntity.getId());
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ProtocolEntityResponse>> getAllProtocol() {
        List<ProtocolEntityResponse> protocolEntityResponseList = this.protocolService.getAllProtocol();

        if (protocolEntityResponseList.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(protocolEntityResponseList);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ProtocolEntityResponse> getProtocolById(@PathVariable Long id) {
        Optional<ProtocolEntityResponse> optProtocolEntityResponse = this.protocolService.getProtocol(id);

        return optProtocolEntityResponse
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
