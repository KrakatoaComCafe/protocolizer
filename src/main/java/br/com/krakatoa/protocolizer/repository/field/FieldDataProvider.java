package br.com.krakatoa.protocolizer.repository.field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FieldDataProvider {

    private final FieldRepository fieldRepository;

    @Autowired
    public FieldDataProvider(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    public List<Field> saveAll(List<Field> fields) {
        return this.fieldRepository.saveAll(fields);
    }

    public List<Field> findAll() {
        return this.fieldRepository.findAll();
    }

    public Optional<Field> findById(Long id) {
        return this.fieldRepository.findById(id);
    }
}
