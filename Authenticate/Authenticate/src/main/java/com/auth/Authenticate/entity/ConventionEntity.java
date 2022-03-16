package com.auth.Authenticate.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "convention")
public class ConventionEntity {
    @Column(name = "cid", nullable = false, unique = true)
    private Integer cid;

    @Column(name = "con_name")
    private String conName;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    @Column(name = "description")
    private String description;

    @Column(name = "con_email")
    private String conEmail;

    @Column(name = "website")
    private String website;

    /// CONSTRUCTOR \\\
    public ConventionEntity() {

    }

    /// GETTERS \\\
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getCid() {
        return this.cid;
    }

    public String getConName() {
        return this.conName;
    }

    public Timestamp getStartDate() {
        return this.startDate;
    }

    public Timestamp getEndDate() {
        return this.endDate;
    }

    public String getDescription() {
        return this.description;
    }

    public String getConEmail() {
        return this.conEmail;
    }

    public String getWebsite() {
        return this.website;
    }

    /// SETTERS \\\
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public void setConName(String conName) {
        this.conName = conName;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setConEmail(String conEmail) {
        this.conEmail = conEmail;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

}
