package br.com.krakatoa.protocolizer.service;

import br.com.krakatoa.protocolizer.entity.model.field.FieldFactory;
import br.com.krakatoa.protocolizer.entity.model.interpretmessage.MessageInterpretFactory;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolDataProvider;
import org.junit.jupiter.api.Nested;

import static org.mockito.Mockito.mock;

class InterpretMessageServiceTest {

    @Nested
    class InterpretTest {

        private final ProtocolDataProvider protocolDataProvider;
        private final ConverterAction converterAction;
        private final FieldAction fieldAction;
        private final FieldFactory fieldFactory;
        private final MessageInterpretFactory messageInterpretFactory;
        private final InterpretMessageService interpretMessageService;

        InterpretTest() {
            this.protocolDataProvider = mock(ProtocolDataProvider.class);
            this.converterAction = mock(ConverterAction.class);
            this.fieldAction = mock(FieldAction.class);
            this.fieldFactory = mock(FieldFactory.class);
            this.messageInterpretFactory = mock(MessageInterpretFactory.class);
            this.interpretMessageService = new InterpretMessageService(this.protocolDataProvider, this.converterAction,
                this.fieldAction, this.fieldFactory, this.messageInterpretFactory);
        }
    }
}