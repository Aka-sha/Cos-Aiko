package com.auth.Authenticate.entity;

/**
 * This EventEntity class performs the Object Relational Mapping (ORM)
 * to the events table in our MySQL database.
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "event")
public class EventEntity {
    @Column(name = "eid", nullable = false, unique = true)
    private Integer eid;

    @Column(name = "title")
    private String title;

    @Column(name = "summary")
    private String summary;

    @Column(name = "location")
    private String location;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE},mappedBy = "events")
    @Access(AccessType.FIELD)
    @JsonIgnore
    List<UserEntity> users;

    /// CONSTRUCTOR \\\
    public EventEntity() {

    }

    /// GETTERS \\\
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getEid() {
        return this.eid;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSummary() {
        return this.summary;
    }

    public String getLocation() {
        return this.location;
    }

    public Time getStartTime() {
        return this.startTime;
    }

    public Time getEndTime() {
        return this.endTime;
    }

    public List<UserEntity> getUsers() { return this.users; }

    /// SETTERS \\\
    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public void setUsers(List<UserEntity> users) { this.users = users; }
}
