package org.lessons.java.springlamiapizzeriacrud.api;

import org.lessons.java.springlamiapizzeriacrud.model.Pizza;
import org.lessons.java.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/pizzas")
public class PizzaRestController {

    @Autowired
    private PizzaRepository pizzaRepository;

    //servizio per avere lista pizze
    @GetMapping
    public List<Pizza> index() {
        return pizzaRepository.findAll();
    }
}
