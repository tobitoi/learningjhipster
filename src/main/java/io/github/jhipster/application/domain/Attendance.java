package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * A Attendance.
 */
@Entity
@Table(name = "attendance")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "check_in_time")
    private String checkInTime;

    @Column(name = "check_out_time")
    private String checkOutTime;

    @Column(name = "description")
    private String description;

    @Column(name = "shift")
    private String shift;

    @Column(name = "check_type")
    private String checkType;

    @Column(name = "verify_code")
    private String verifyCode;

    @Column(name = "sensor_id")
    private String sensorId;

    @Column(name = "work_code")
    private Integer workCode;

    @Column(name = "sn")
    private String sn;

    @ManyToOne
    private Employee employee;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public Attendance checkInTime(String checkInTime) {
        this.checkInTime = checkInTime;
        return this;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public Attendance checkOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
        return this;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public String getDescription() {
        return description;
    }

    public Attendance description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShift() {
        return shift;
    }

    public Attendance shift(String shift) {
        this.shift = shift;
        return this;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getCheckType() {
        return checkType;
    }

    public Attendance checkType(String checkType) {
        this.checkType = checkType;
        return this;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public Attendance verifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
        return this;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getSensorId() {
        return sensorId;
    }

    public Attendance sensorId(String sensorId) {
        this.sensorId = sensorId;
        return this;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public Integer getWorkCode() {
        return workCode;
    }

    public Attendance workCode(Integer workCode) {
        this.workCode = workCode;
        return this;
    }

    public void setWorkCode(Integer workCode) {
        this.workCode = workCode;
    }

    public String getSn() {
        return sn;
    }

    public Attendance sn(String sn) {
        this.sn = sn;
        return this;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Attendance employee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Attendance attendance = (Attendance) o;
        if (attendance.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), attendance.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Attendance{" +
            "id=" + getId() +
            ", checkInTime='" + getCheckInTime() + "'" +
            ", checkOutTime='" + getCheckOutTime() + "'" +
            ", description='" + getDescription() + "'" +
            ", shift='" + getShift() + "'" +
            ", checkType='" + getCheckType() + "'" +
            ", verifyCode='" + getVerifyCode() + "'" +
            ", sensorId='" + getSensorId() + "'" +
            ", workCode=" + getWorkCode() +
            ", sn='" + getSn() + "'" +
            "}";
    }
}
