package com.heroes.super_heroes;

import jakarta.persistence.*;

@Entity //maps table to entity class
@Table(name = "hero_stats")
public class HeroStats {
    @Id // every entity needs exactly one @Id field
    @GeneratedValue(strategy = GenerationType.IDENTITY) // tells db to automaticalky generate values for this field
    private Long id; // so if u ever save a new hero, u don't set the id, the db does it auto

    private Integer year;

    @Column(name = "film_name") //when column name is not exactly the var name
    private String filmName;

    private String director; // no column annotate needed
    private String publisher;
    private String distributer;

    @Column(name = "worldwide_gross")
    private String worldwideGross;

    private String description;

    // default constructor (not paramaterised)
    public HeroStats() {}

    // parameter version
    public HeroStats(Long id, Integer year, String filmName, String director, String publisher, String distributer, String worldwideGross, String description) {
        this.id = id;
        this.year = year;
        this.filmName = filmName;
        this.director = director;
        this.publisher = publisher;
        this.distributer = distributer;
        this.worldwideGross = worldwideGross;
        this.description = description;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public String getFilmName() { return filmName; }
    public void setFilmName(String filmName) { this.filmName = filmName; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getDistributer() { return distributer; }
    public void setDistributer(String distributer) { this.distributer = distributer; }

    public String getWorldwideGross() { return worldwideGross; }
    public void setWorldwideGross(String worldwideGross) { this.worldwideGross = worldwideGross; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}