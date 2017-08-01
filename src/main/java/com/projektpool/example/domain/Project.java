package com.projektpool.example.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

/*
 * a simple domain entity doubling as a DTO
 */
@Entity
@Table(name = "project")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Project {

    @Id
    @GeneratedValue()
    private long id;

    @Column(nullable = false)
    private String projektname;

    @Column()
    private String beschreibung;

    public Project() {
    }

    public Project(String name, String description) {
        this.projektname = name;
        this.beschreibung = description;
    }

    public long getId() {
        return this.id;
    }

    // for tests ONLY
    public void setId(long id) {
        this.id = id;
    }

    public String getProjektname() {
        return projektname;
    }

    public void setProjektname(String name) {
        this.projektname = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String description) {
        this.beschreibung = description;
    }


    @Override
    public String toString() {
        return "Project {" +
                "id=" + id +
                ", name1='" + projektname + '\'' +
                ", description1='" + beschreibung + '\'' +
                '}';
    }
}
