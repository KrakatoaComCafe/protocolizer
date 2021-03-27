package br.com.krakatoa.protocolizer.repository.protocol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProtocolDataProvider {

    @Autowired
    private ProtocolRepository protocolRepository;

    public ProtocolEntity save(ProtocolEntity protocolEntity) {
        return this.protocolRepository.save(protocolEntity);
    }

    public ProtocolEntity findOneByNameAndVersion(String name, String version) {
        return this.protocolRepository.findOneByNameAndVersion(name, version);
    }

    public Optional<ProtocolEntity> findById(Long id) {
        return this.protocolRepository.findById(id);
    }

    public List<ProtocolEntity> findAll() {
        return this.protocolRepository.findAll();
    }
}
