package br.com.krakatoa.protocolizer;

import br.com.krakatoa.protocolizer.repository.protocol.ProtocolDataProvider;
import br.com.krakatoa.protocolizer.service.ConverterService;
import br.com.krakatoa.protocolizer.service.InterpretMessageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableSwagger2
public class ProtocolizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProtocolizerApplication.class, args);
    }

    @Bean
    public InterpretMessageService messageServiceBean(ProtocolDataProvider protocolDataProvider, ConverterService converterService) {
        return new InterpretMessageService(protocolDataProvider, converterService);
    }

    @Bean
    public ConverterService converterServiceBean() {
        return new ConverterService();
    }
}
