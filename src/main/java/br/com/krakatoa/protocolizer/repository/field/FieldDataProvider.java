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

    public List<FieldEntity> saveAll(List<FieldEntity> fieldEntityEntities) {
        return this.fieldRepository.saveAll(fieldEntityEntities);
    }

    public List<FieldEntity> findAll() {
        return this.fieldRepository.findAll();
    }

    public Optional<FieldEntity> findById(Long id) {
        return this.fieldRepository.findById(id);
    }
}
