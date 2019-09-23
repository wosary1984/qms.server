package qms.repository.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import qms.repository.base.BaseEntity;

@Entity
@Table(name = "T_KEY_FACTOR")
public class KeyFactor extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "c_factor_key", unique = true, nullable = true)
    String key;

    @Column(name = "c_factor_name", unique = false, nullable = true)
    String name;

    @Column(name = "c_factor_desc")
    String desc;

    @OneToMany(mappedBy = "factor", orphanRemoval = true, cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    Set<FactorEvent> events = new HashSet<FactorEvent>();

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Set<FactorEvent> getEvents() {
        return events;
    }

    public void setEvents(Set<FactorEvent> events) {
        this.events = events;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}