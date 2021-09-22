package br.com.krakatoa.protocolizer.service;

import br.com.krakatoa.protocolizer.entity.response.entity.ProtocolEntityResponse;
import br.com.krakatoa.protocolizer.entity.response.entity.ProtocolEntityResponseFactory;
import br.com.krakatoa.protocolizer.entity.form.ProtocolForm;
import br.com.krakatoa.protocolizer.repository.field.FieldDataProvider;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolDataProvider;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProtocolService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProtocolService.class);

    private final ProtocolDataProvider protocolDataProvider;
    private final FieldDataProvider fieldDataProvider;
    private final ProtocolEntityResponseFactory protocolEntityResponseFactory;


    @Autowired
    public ProtocolService(ProtocolDataProvider protocolDataProvider, FieldDataProvider fieldDataProvider,
                           ProtocolEntityResponseFactory protocolEntityResponseFactory) {
        this.protocolDataProvider = protocolDataProvider;
        this.fieldDataProvider = fieldDataProvider;
        this.protocolEntityResponseFactory = protocolEntityResponseFactory;
    }

    public ProtocolEntity save(ProtocolForm protocolForm) {
        ProtocolEntity protocolEntity = protocolForm.convertToProtocol();

        //todo use json format in H2
        this.protocolDataProvider.save(protocolEntity);
        this.fieldDataProvider.saveAll(protocolEntity.getFieldEntityList());

        return protocolEntity;
    }

    public Optional<ProtocolEntityResponse> getProtocol(Long id) {
        Optional<ProtocolEntity> optProtocol = this.protocolDataProvider.findById(id);
        if (optProtocol.isEmpty()) LOGGER.info("No protocol was found using id [{}]", id);

        return Optional.of(this.protocolEntityResponseFactory.create(optProtocol.get()));
    }

    public List<ProtocolEntityResponse> getAllProtocol() {
        List<ProtocolEntity> protocolEntityList = this.protocolDataProvider.findAll();

        return protocolEntityList.stream()
            .map(this.protocolEntityResponseFactory::create)
            .collect(Collectors.toList());
    }
}
