package br.com.krakatoa.protocolizer.repository.protocol;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public class ProtocolDataProvider {

    private final ProtocolRepository protocolRepository;

    @Autowired
    public ProtocolDataProvider(ProtocolRepository protocolRepository) {
        this.protocolRepository = protocolRepository;
    }

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
