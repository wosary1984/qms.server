package qms.repository.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import qms.repository.base.BaseEntity;

@Entity
@Table(name = "T_RELATED_FACTOR")
public class EventRelatedFactor extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "c_key", nullable = true)
    String key;

    @Column(name = "c_type", nullable = true)
    String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "c_fk")
    FactorEvent event;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    

    @JsonIgnore
    public FactorEvent getEvent() {
        return event;
    }

    public void setEvent(FactorEvent event) {
        this.event = event;
    }

    public EventRelatedFactor(String key, String type, FactorEvent event) {
        this.key = key;
        this.type = type;
        this.event = event;
    }

    public EventRelatedFactor() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}