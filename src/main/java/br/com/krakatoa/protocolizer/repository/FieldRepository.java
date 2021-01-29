package br.com.krakatoa.protocolizer.repository;

import br.com.krakatoa.protocolizer.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<Field, Long> {
}
