package qms.repository.wafer;

import java.io.Serializable;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "T_WAFER")
public class Wafer implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -5939742857358977077L;

    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "c_wafer_id", insertable = true, unique = true, nullable = false)
    String waferid;

    @Column(name = "c_radius")
    long radius;

    @OneToMany(mappedBy = "wafer", orphanRemoval = true, cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @OrderBy(value = "id asc")
    Set<WaferData> data = new HashSet<WaferData>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWaferid() {
        return waferid;
    }

    public void setWaferid(String waferid) {
        this.waferid = waferid;
    }

    public long getRadius() {
        return radius;
    }

    public void setRadius(long radius) {
        this.radius = radius;
    }

    public Set<WaferData> getData() {
        return data;
    }

    public void setData(Set<WaferData> data) {
        this.data = data;
    }

    public Wafer() {

    }

    public Wafer(String waferid, long radius) {
        this.waferid = waferid;
        this.radius = radius;
    }

    public Wafer(String waferid, long radius, Set<WaferData> data) {
        this.waferid = waferid;
        this.radius = radius;
        this.data = data;
    }
}