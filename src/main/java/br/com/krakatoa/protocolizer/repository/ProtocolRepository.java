package br.com.krakatoa.protocolizer.repository;

import br.com.krakatoa.protocolizer.model.Protocol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProtocolRepository extends JpaRepository<Protocol, Long> {

}
