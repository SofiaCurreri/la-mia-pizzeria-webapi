package org.lessons.java.springlamiapizzeriacrud.api;

import org.lessons.java.springlamiapizzeriacrud.model.Pizza;
import org.lessons.java.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/pizzas")
public class PizzaRestController {

    @Autowired
    private PizzaRepository pizzaRepository;

    //servizio per avere lista pizze
    @GetMapping
    public List<Pizza> index() {
        return pizzaRepository.findAll();
    }

    //servizio per dettaglio singola Pizza
    @GetMapping("/{id}")
    public Pizza get(@PathVariable Integer id) {
        //cerco pizza con quell' id sul db
        Optional<Pizza> pizza = pizzaRepository.findById(id);
        if (pizza.isPresent()) {
            return pizza.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
