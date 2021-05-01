package br.com.krakatoa.protocolizer.model.interpretmessage;

import br.com.krakatoa.protocolizer.form.MessageChangeForm;
import br.com.krakatoa.protocolizer.model.interpretmessage.messagefields.MessageValuesFactory;
import br.com.krakatoa.protocolizer.model.interpretmessage.protocol.Protocol;
import br.com.krakatoa.protocolizer.model.interpretmessage.protocol.ProtocolFactory;
import br.com.krakatoa.protocolizer.model.interpretmessage.protocol.fielddefinition.FixedDefinition;
import br.com.krakatoa.protocolizer.model.interpretmessage.protocol.fielddefinition.FieldDefinitionFactory;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolEntity;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class MessageInterpretFactoryTest {

    private final FieldDefinitionFactory fieldDefinitionFactory;
    private final ProtocolFactory protocolFactory;
    private final MessageInterpretFactory messageInterpretFactory;
    private final MessageValuesFactory messageValuesFactory;

    public MessageInterpretFactoryTest() {
        this.fieldDefinitionFactory = mock(FieldDefinitionFactory.class);
        this.protocolFactory = mock(ProtocolFactory.class);
        this.messageValuesFactory = mock(MessageValuesFactory.class);
        this.messageInterpretFactory = new MessageInterpretFactory(this.fieldDefinitionFactory, this.protocolFactory, this.messageValuesFactory);
    }

    @Test
    void given() {
        ProtocolEntity protocolEntity = mock(ProtocolEntity.class);
        MessageChangeForm messageChangeForm = mock(MessageChangeForm.class);
        String bitmap = "";
        String rawData = "12345123";
        FixedDefinition fixedDefinition = mock(FixedDefinition.class);
        List<FixedDefinition> fixedDefinitionList = Collections.singletonList(fixedDefinition);
        Protocol protocol = mock(Protocol.class);
        List<String> fieldActivatedList = Collections.singletonList("Field002");

        doReturn(fixedDefinitionList)
                .when(this.fieldDefinitionFactory)
                .createList(protocolEntity);

        this.messageInterpretFactory.create(protocolEntity, messageChangeForm);
    }
}