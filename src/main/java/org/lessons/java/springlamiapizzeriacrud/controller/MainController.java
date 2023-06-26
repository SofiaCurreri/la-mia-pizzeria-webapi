package org.lessons.java.springlamiapizzeriacrud.controller;

import org.lessons.java.springlamiapizzeriacrud.model.Pizza;
import org.lessons.java.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class MainController {
    //PizzaRepository mi dà accesso al db

    @Autowired
    //quando serve pizzaRepository al mio controller, crea da solo un' istanza e me lo passa; equivale ad iniettare la dipendenza da PizzaRepository
    private PizzaRepository pizzaRepository;

//    @GetMapping
//    public String menu(Model model) {
//        List<Pizza> menu = pizzaRepository.findAll();
//        if (menu.isEmpty()) {
//            model.addAttribute("message", "Il menù è vuoto, mi dispiace!");
//        }
//        model.addAttribute("menu", menu);
//        return "index";
//    }

    //metodo che puo ricevere un parametro da query string, se param c' è filtriamo menu, se non c'è mostriamo tutte le pizze
    //keyword è nome parametro che vedremo nell' url
    @GetMapping
    public String menu(@RequestParam(name = "keyword", required = false) String searchString, Model model) {
        //model = mappa di attributi che controller passa alla view

        List<Pizza> menu;

        //se non ho searchString faccio query generica
        if (searchString == null || searchString.isBlank()) {
            //recupero lista pizze da db
            menu = pizzaRepository.findAll();
        } else {
            //se ho searchString faccio query con filtro
//            menu = pizzaRepository.findByName(searchString);
            menu = pizzaRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(searchString, searchString);
        }

        if (menu.isEmpty()) {
            model.addAttribute("message", "Il menù è vuoto, mi dispiace!");
        }
        model.addAttribute("menu", menu);
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer pizzaId, Model model) {
        //.findById gestisce un oggetto di tipo Optional che gestisce anche caso in cui arrivi un Id non sistente
        Optional<Pizza> result = pizzaRepository.findById(pizzaId);
        if (result.isPresent()) {
            //passa la pizza alla view
            model.addAttribute("pizza", result.get());
            return "show";
        } else {
            //ritorno http status 404
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Pizza with id = " + pizzaId + " not found, sorry!");
        }


    }
}
