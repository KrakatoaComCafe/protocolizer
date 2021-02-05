package br.com.krakatoa.protocolizer.repository.field;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//todo change name to EntityRepository
public interface FieldRepository extends JpaRepository<Field, Long> {
}
