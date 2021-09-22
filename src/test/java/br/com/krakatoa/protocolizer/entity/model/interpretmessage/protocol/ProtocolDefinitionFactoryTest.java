package br.com.krakatoa.protocolizer.entity.model.interpretmessage.protocol;

import br.com.krakatoa.protocolizer.entity.model.interpretmessage.protocol.fielddefinition.FieldDefinition;
import br.com.krakatoa.protocolizer.entity.model.interpretmessage.protocol.fielddefinition.FixedDefinition;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class ProtocolDefinitionFactoryTest {

    private final ProtocolDefinitionFactory protocolDefinitionFactory;

    public ProtocolDefinitionFactoryTest() {
        this.protocolDefinitionFactory = new ProtocolDefinitionFactory();
    }

    @Test
    @DisplayName("Throw NullPointerException when ProtocolEntity is null")
    void given_NullProtocolEntity_When_Create_ThenThrowNullPointerException() {
        FixedDefinition fixedDefinition = mock(FixedDefinition.class);
        List<FieldDefinition> fieldDefinitionList = Arrays.asList(fixedDefinition, fixedDefinition);

        assertThrows(NullPointerException.class, () -> {
            this.protocolDefinitionFactory.create(null, fieldDefinitionList);
        });
    }

    @Test
    @DisplayName("Throw NullPointerException when ProtocolEntity is null")
    void given_NullProtocolEntity_When_Create_ThenThrowNullPointerExceasdption() {
        ProtocolEntity protocolEntity = mock(ProtocolEntity.class);

        doReturn("FakeISO")
            .when(protocolEntity)
            .getName();
        doReturn("21.1")
            .when(protocolEntity)
            .getVersion();

        ProtocolDefinition result = this.protocolDefinitionFactory.create(protocolEntity, null);

        assertNotNull(result);
        assertEquals("FakeISO", result.getName());
        assertEquals("21.1", result.getVersion());
        assertNotNull(result.getFieldDefinitionList());
        assertTrue(result.getFieldDefinitionList().isEmpty());
    }

    @Test
    @DisplayName("Create Protocol when use Protocol Entity and a list of FieldDefinition")
    void given_ProtocolEntityAndFieldDefinitionList_When_Create_Then_ReturnProtocol() {
        ProtocolEntity protocolEntity = mock(ProtocolEntity.class);
        FixedDefinition fixedDefinition = mock(FixedDefinition.class);
        List<FieldDefinition> fieldDefinitionList = Arrays.asList(fixedDefinition, fixedDefinition);

        doReturn("FakeISO")
            .when(protocolEntity)
            .getName();
        doReturn("21.1")
            .when(protocolEntity)
            .getVersion();

        ProtocolDefinition result = this.protocolDefinitionFactory.create(protocolEntity, fieldDefinitionList);

        assertNotNull(result);
        assertEquals("FakeISO", result.getName());
        assertEquals("21.1", result.getVersion());
        assertNotNull(result.getFieldDefinitionList());
        assertEquals(2, result.getFieldDefinitionList().size());
    }
}