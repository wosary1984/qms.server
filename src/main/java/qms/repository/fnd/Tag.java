package qms.repository.fnd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import qms.repository.base.BaseEntity;

@Entity
@Table(name = "T_TAG")
public class Tag extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "c_tag_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tagid;

    @Column(name = "c_tag_object", nullable = true)
    long objectid;

    @Column(name = "c_tag")
    String tag;

    @Column(name = "c_is_active")
    boolean active;
}