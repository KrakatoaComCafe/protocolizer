package br.com.krakatoa.protocolizer.repository.field;

import br.com.krakatoa.protocolizer.repository.protocol.ProtocolEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;

import javax.persistence.Entity;
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
@Table(name="field")
public class FieldEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int length;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "protocol_id")
    private ProtocolEntity protocol;

    public FieldEntity() {
    }

    public FieldEntity(String name, int length, ProtocolEntity protocolEntity) {
        this.name = name;
        this.length = length;
        this.protocol = protocolEntity;
    }
}
