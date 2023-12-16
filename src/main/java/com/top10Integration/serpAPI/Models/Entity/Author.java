package com.top10Integration.serpAPI.Models.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "author_ID", nullable = false, length = 50)
    private String author_ID;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;
    @Column(name = "author_url", nullable = false, length = 100)
    private String author_url;

    @Column(name = "affiliations", nullable = false, length = 100)
    private String affiliations;

    @Column(name = "tarticles", nullable = false, length = 3)
    private Integer tarticles;

    public Author() {

    }

    public Author(String name){
        this.name = name;
    }

    public Author(String name, String author_ID){
        this.name = name;
        this.author_ID = author_ID;
    }

    public Author(Integer id, String author_ID, String name, String email, String affiliations, String author_url, Integer tarticles) {
        this.id = id;
        this.author_ID = author_ID;
        this.name = name;
        this.email = email;
        this.affiliations = affiliations;
        this.author_url = author_url;
        this.tarticles = tarticles;
    }

    public Author(String author_ID, String name, String email, String affiliations, String author_url, Integer tarticles) {
        this.author_ID = author_ID;
        this.name = name;
        this.email = email;
        this.affiliations = affiliations;
        this.author_url = author_url;
        this.tarticles = tarticles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor_ID() {
        return author_ID;
    }

    public void setAuthor_ID(String author_ID) {
        this.author_ID = author_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthor_url() {
        return author_url;
    }

    public void setAuthor_url(String author_url) {
        this.author_url = author_url;
    }

    public String getAffiliations() {
        return affiliations;
    }

    public void setAffiliations(String affiliations) {
        this.affiliations = affiliations;
    }

    public Integer getTarticles() {
        return tarticles;
    }

    public void setTarticles(Integer tarticles) {
        this.tarticles = tarticles;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", author_ID='" + author_ID + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", author_url='" + author_url + '\'' +
                ", affiliations='" + affiliations + '\'' +
                ", tarticles='" + tarticles + '\'' +
                '}';
    }
}
