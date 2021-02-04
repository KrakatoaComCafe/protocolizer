package br.com.krakatoa.protocolizer.repository.field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FieldDataProvider {

    private final FieldRepository fieldRepository;

    @Autowired
    public FieldDataProvider(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    public Field save(Field field) {
        return this.fieldRepository.save(field);
    }

    public List<Field> saveAll(List<Field> fields) {
        return this.fieldRepository.saveAll(fields);
    }
}
