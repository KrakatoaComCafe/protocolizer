package br.com.krakatoa.protocolizer.repository.field;

import org.springframework.data.jpa.repository.JpaRepository;

//todo change name to EntityRepository
public interface FieldRepository extends JpaRepository<FieldEntity, Long> {
}
