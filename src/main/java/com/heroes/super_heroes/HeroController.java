package com.heroes.super_heroes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// controller layer handles incoming http reqs, validates them to the service layer and returns appropriate response
@RestController // marks class as spring MVC controller where every method returns domain obj instead of a view
@RequestMapping(path = "api/v1/hero")
public class HeroController
{
    private final HeroService heroService;

    @Autowired //allows controller to delegate business logic back to service layer
    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping // handle all GET reqs
    public List<HeroStats> getHeroes(
            @RequestParam(required = false) String filmName,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String director,
            @RequestParam(required = false) String publisher,
            @RequestParam(required = false) String distributer,
            @RequestParam(required = false) Long worldwideGross,
            @RequestParam(required = false) String description
    ) {

        // method will return diff hero list based on the presence of certain query params
        // so for example if no params then gets ALL

        if (filmName != null && year != null) {
            return heroService.getHeroesByNameAndYear(filmName, year);
        }
        else if (filmName != null) {
            return heroService.getHeroesByName(filmName);
        }
        else if (year != null) {
            return heroService.getHeroesFromYear(year);
        }
        else if (director != null) {
            return heroService.getHeroesByDirector(director);
        }
//        else if (publisher != null) { can add these later in the future
//            return heroService.getHeroesByPublisher(publisher);
//        }
//        else if (distributer != null) {
//            return heroService.getHeroesByDistributer(distributer);
//        }
//        else if (worldwideGross != null) {
//            return heroService.getHeroesByWorldwideGross(worldwideGross);
//        }
//        else if (description != null) {
//            return heroService.getHeroesByDescription(description);
//        }
        else {
            // No params provided, return all heroes
            return heroService.getHeroes();
        }
    }
}