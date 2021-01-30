package br.com.krakatoa.protocolizer.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Protocol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String version;
    private String encoding;
    @OneToMany(mappedBy = "protocol")
    @Setter
    private List<Field> fields = new ArrayList<>();

    public Protocol(String name, String version, String encoding) {
        this.name = name;
        this.version = version;
        this.encoding = encoding;
    }

}
