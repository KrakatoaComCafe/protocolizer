package br.com.krakatoa.protocolizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableSwagger2
@ComponentScan
public class ProtocolizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProtocolizerApplication.class, args);
    }
}
