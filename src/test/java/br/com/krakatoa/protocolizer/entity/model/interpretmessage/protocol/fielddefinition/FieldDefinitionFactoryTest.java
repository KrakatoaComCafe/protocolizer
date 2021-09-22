package br.com.krakatoa.protocolizer.entity.model.interpretmessage.protocol.fielddefinition;

import br.com.krakatoa.protocolizer.repository.field.FieldEntity;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class FieldDefinitionFactoryTest {

    private final FieldDefinitionFactory fieldDefinitionFactory;

    public FieldDefinitionFactoryTest() {
        this.fieldDefinitionFactory = new FieldDefinitionFactory();
    }

    @Test
    @DisplayName("Return empty list when protocol entity is null")
    void given_NullProtocol_When_CreateList_Then_ReturnEmptyList() {
        ProtocolEntity protocolEntity = null;
        List<FieldDefinition> resultlist = this.fieldDefinitionFactory.createList(protocolEntity);

        assertNotNull(resultlist);
        assertTrue(resultlist.isEmpty());
    }

    @Test
    @DisplayName("Return empty list when Protocol Entity don't have Field Entity")
    void given_ProtocolEntityWithNoFieldEntity_When_CreateList_Then_ReturnEmptyList() {
        ProtocolEntity protocolEntity = mock(ProtocolEntity.class);

        doReturn(Collections.emptyList())
                .when(protocolEntity)
                .getFieldEntityList();

        List<FieldDefinition> results = this.fieldDefinitionFactory.createList(protocolEntity);

        assertNotNull(results);
        assertTrue(results.isEmpty());
    }

    @Test
    @DisplayName("Return Field list of all Field Entity in Protocol Entity")
    void given_FieldEntity_When_CreateList_Then_ReturnFieldList() {
        ProtocolEntity protocolEntity = mock(ProtocolEntity.class);
        FieldEntity fieldEntity = mock(FieldEntity.class);
        List<FieldEntity> fieldEntityList = Arrays.asList(fieldEntity, fieldEntity);

        doReturn(fieldEntityList)
                .when(protocolEntity)
                .getFieldEntityList();
        doReturn("Field002").doReturn("Field003")
                .when(fieldEntity)
                .getName();
        doReturn(8).doReturn(6)
                .when(fieldEntity)
                .getLength();

        List<FieldDefinition> resultList = this.fieldDefinitionFactory.createList(protocolEntity);

        assertNotNull(resultList);
        assertEquals(2, resultList.size());
        assertEquals("Field002", resultList.get(0).getName());
        assertEquals(8, resultList.get(0).getLength());
        assertEquals("Field003", resultList.get(1).getName());
        assertEquals(6, resultList.get(1).getLength());
    }

}