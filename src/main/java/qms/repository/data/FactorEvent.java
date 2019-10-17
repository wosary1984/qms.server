package qms.repository.data;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import qms.repository.base.BaseEntity;

@Entity
@Table(name = "T_FACTOR_EVENTS")
public class FactorEvent extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "c_event_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventid;

    @Column(name = "c_event_title", nullable = true)
    String title;

    @Column(name = "c_event_content")
    String content;

    @Column(name = "c_event_year")
    int year;

    @Column(name = "c_event_month")
    int month;

    @Column(name = "c_event_day")
    int day;

    @Column(name = "c_event_date", unique = false, nullable = true)
    Date date;

    @Column(name = "c_event_date_bc")
    Boolean bc;

    @Column(name = "c_event_country")
    Date country;

    @Column(name = "c_event_city")
    Date city;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "c_keyid")
    Factor factor;

    @OneToMany(mappedBy = "event", orphanRemoval = true, cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    Set<EventRelatedFactor> relatedFactors = new HashSet<EventRelatedFactor>();

    public Long getEventid() {
        return eventid;
    }

    public void setEventid(Long eventid) {
        this.eventid = eventid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDate() {
        if (this.bc)
            return null;

        Calendar cal = Calendar.getInstance();
        cal.setTime(this.date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return Integer.toString(year) + "/" + Integer.toString(month) + "/" + Integer.toString(day);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getCountry() {
        return country;
    }

    public void setCountry(Date country) {
        this.country = country;
    }

    public Date getCity() {
        return city;
    }

    public void setCity(Date city) {
        this.city = city;
    }

    @JsonIgnore
    public Factor getFactor() {
        return factor;
    }

    public void setFactor(Factor factor) {
        this.factor = factor;
    }

    public Boolean getBc() {
        return bc;
    }

    public void setBc(Boolean bc) {
        this.bc = bc;
    }

    public Set<EventRelatedFactor> getRelatedFactors() {
        return relatedFactors;
    }

    public void setRelatedFactors(Set<EventRelatedFactor> relatedFactors) {
        this.relatedFactors = relatedFactors;
    }
}