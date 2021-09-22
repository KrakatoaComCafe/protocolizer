package br.com.krakatoa.protocolizer.repository.field;

import br.com.krakatoa.protocolizer.format.FieldType;
import br.com.krakatoa.protocolizer.repository.protocol.ProtocolEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Getter
@Table(name = "field")
public class FieldEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private FieldType type;
    private int length;
    private int maxLength;
    private int minLength;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "protocol_id")
    private ProtocolEntity protocol;

    public FieldEntity() {
    }

    public FieldEntity(String name, FieldType type, int length, int maxLength, int minLength, ProtocolEntity protocolEntity) {
        this.name = name;
        this.type = type;
        this.length = length;
        this.maxLength = maxLength;
        this.minLength = minLength;
        this.protocol = protocolEntity;
    }
}
