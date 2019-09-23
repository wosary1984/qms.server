package qms.repository.data;

import java.util.Date;

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
@Table(name = "T_KEY_FACTOR_EVENTS")
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

    @Column(name = "c_event_country")
    Date country;

    @Column(name = "c_event_city")
    Date city;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "c_keyid")
    KeyFactor factor;

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

    public Date getDate() {
        return date;
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
    public KeyFactor getFactor() {
        return factor;
    }

    public void setFactor(KeyFactor factor) {
        this.factor = factor;
    }
}