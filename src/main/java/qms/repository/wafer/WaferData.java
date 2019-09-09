package qms.repository.wafer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "T_WAFER_DATA")
public class WaferData implements Serializable {
    private static final long serialVersionUID = 3428483429166699L;

    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "c_wafer_id", insertable = true, unique = false, nullable = true)
    String waferid;

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "waferid")
	Wafer wafer;

    @Column(name = "c_cx")
    long cx;

    @Column(name = "c_cy")
    long cy;

    @Column(name = "c_delta")
    long delta;

    @Column(name = "c_defect")
    private boolean defect;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCx() {
        return cx;
    }

    public void setCx(int cx) {
        this.cx = cx;
    }

    public long getCy() {
        return cy;
    }

    public void setCy(int cy) {
        this.cy = cy;
    }

    public boolean isDefect() {
        return defect;
    }

    public void setDefect(boolean defect) {
        this.defect = defect;
    }

    public WaferData() {

    }

    public WaferData(String waferid, long cx, long cy, long delta, boolean defect) {
        this.waferid = waferid;
        this.cx = cx;
        this.cy = cy;
        this.delta = delta;
        this.defect = defect;
    }

    public WaferData(Wafer wafer, long cx, long cy, long delta, boolean defect) {
        this.wafer = wafer;
        this.cx = cx;
        this.cy = cy;
        this.delta = delta;
        this.defect = defect;
    }

    public String getWaferid() {
        return waferid;
    }

    public void setWaferid(String waferid) {
        this.waferid = waferid;
    }

    public long getDelta() {
        return delta;
    }

    public void setDelta(long delta) {
        this.delta = delta;
    }

}
