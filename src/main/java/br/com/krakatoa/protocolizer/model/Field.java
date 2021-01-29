package br.com.krakatoa.protocolizer.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int maxLength;
    private int minLength;
    @ManyToOne
    private Protocol protocol;

    public Field(String name, int minLength, int maxLength, Protocol protocol) {
        this.name = name;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.protocol = protocol;
    }

}
