package br.com.krakatoa.protocolizer.repository.protocol;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProtocolRepository extends JpaRepository<Protocol, Long> {

    Protocol findOneByNameAndVersion(String name, String version);

    List<Protocol> findByName(String name);
}
