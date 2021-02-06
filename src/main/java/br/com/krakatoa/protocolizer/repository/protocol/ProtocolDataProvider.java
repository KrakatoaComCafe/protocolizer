package br.com.krakatoa.protocolizer.repository.protocol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProtocolDataProvider {

    @Autowired
    private ProtocolRepository protocolRepository;

    public Protocol save(Protocol protocol) {
        return this.protocolRepository.save(protocol);
    }

    public Protocol findOneByNameAndVersion(String name, String version) {
        return this.protocolRepository.findOneByNameAndVersion(name, version);
    }

    public Optional<Protocol> findById(Long id) {
        return this.protocolRepository.findById(id);
    }

    public List<Protocol> findAll() {
        return this.protocolRepository.findAll();
    }
}
