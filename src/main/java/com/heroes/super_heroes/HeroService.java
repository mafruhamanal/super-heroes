package com.heroes.super_heroes;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class HeroService {
    private final HeroRepository heroRepository;

    @Autowired // enables interactions w db
    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    // business logic stuff
    public List<HeroStats> getHeroes() {
        return heroRepository.findAll(); // returns list of every single hero
    }

    public List<HeroStats> getHeroesFromYear(Integer year) {
        return heroRepository.findAll().stream().filter(hero -> year.equals((hero.getYear()))).collect(Collectors.toList());
    }

    public List<HeroStats> getHeroesByName(String film) { // for searching by film name, for exact can use equals, but contains makes search better
        return heroRepository.findAll().stream().filter(hero -> hero.getFilmName().toLowerCase().contains(film.toLowerCase())).collect(Collectors.toList());
    }

    public List<HeroStats> getHeroesByDirector(String director) { // for searching by director name, for exact can use equals, but contains makes search better
        return heroRepository.findAll().stream().filter(hero -> hero.getDirector().toLowerCase().contains(director.toLowerCase())).collect(Collectors.toList());
    }

    public List<HeroStats> getHeroesByNameAndYear(String film, Integer year ) {
        return heroRepository.findAll().stream().filter(hero -> year.equals((hero.getYear())) && hero.getFilmName().toLowerCase().contains(film.toLowerCase())).collect(Collectors.toList());
    }

    public HeroStats addHero(HeroStats hero) {
        heroRepository.save(hero);
        return hero;
    }

    public HeroStats updateHero(HeroStats updated_hero) {
        Optional<HeroStats> existingHero = heroRepository.findByName(updated_hero.getFilmName());

        if (existingHero.isPresent()) {
            HeroStats heroToUpdate = existingHero.get();
            heroToUpdate.setYear(updated_hero.getYear());
            heroToUpdate.setFilmName(updated_hero.getFilmName());
            heroToUpdate.setDirector(updated_hero.getDirector());
            heroToUpdate.setPublisher(updated_hero.getPublisher());
            heroToUpdate.setDistributer(updated_hero.getDistributer());
            heroToUpdate.setWorldwideGross(updated_hero.getWorldwideGross());
            heroToUpdate.setDescription(updated_hero.getDescription());

            heroRepository.save(heroToUpdate);
            return heroToUpdate;
        }

        return null; // or throw an exception if hero not found
    }
    @Transactional // makes sure this is part of a trans, for data integrity
    public void deleteHero(String filmName) {
        heroRepository.deleteByFilmName(filmName);
    }
}
