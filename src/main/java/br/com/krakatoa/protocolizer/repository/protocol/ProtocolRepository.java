package br.com.krakatoa.protocolizer.repository.protocol;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProtocolRepository extends JpaRepository<ProtocolEntity, Long> {

    ProtocolEntity findOneByNameAndVersion(String name, String version);
}
