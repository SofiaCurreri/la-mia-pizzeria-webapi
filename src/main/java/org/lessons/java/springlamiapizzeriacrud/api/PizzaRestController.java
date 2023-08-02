package org.lessons.java.springlamiapizzeriacrud.api;

import jakarta.validation.Valid;
import org.lessons.java.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import org.lessons.java.springlamiapizzeriacrud.model.Pizza;
import org.lessons.java.springlamiapizzeriacrud.repository.PizzaRepository;
import org.lessons.java.springlamiapizzeriacrud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private PizzaService pizzaService;

    //servizio per avere lista pizze
    @GetMapping
    public List<Pizza> index(@RequestParam Optional<String> keyword) {
        return pizzaService.getAll(keyword);
    }

    //servizio per dettaglio singola Pizza
    @GetMapping("/{id}")
    public Pizza get(@PathVariable Integer id) {
        //cerco pizza con quell' id sul db
//        Optional<Pizza> pizza = pizzaRepository.findById(id);
//        if (pizza.isPresent()) {
//            return pizza.get();
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
        try {
            return pizzaService.getById(id);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    //servizio per creare nuova pizza
    //solo post e put hanno body, ovvero RequestBody
    @PostMapping
    public Pizza create(@Valid @RequestBody Pizza pizza) {
        return pizzaService.create(pizza);
    }

    //servizio per cancellare pizza
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        pizzaRepository.deleteById(id);
    }

    //servizio per modificare pizza
    //con la put devo passare per forza id della risorsa da modificare + body(=dati che andranno a sostituire i dati della risorsa),
    //quindi va passato l' oggetto intero anche se viene cambiato un solo campo
    @PutMapping("/{id}")
    public Pizza update(@Valid @PathVariable Integer id, @RequestBody Pizza pizza) {
        pizza.setId(id);
        return pizzaRepository.save(pizza);
    }

    //Pageable dimostrativo. Caratteristiche di solito: page, size e sort
    @GetMapping("/page")
    public Page<Pizza> page(@RequestParam(defaultValue = "3") Integer size, @RequestParam(defaultValue = "0") Integer page) {
        //creo pageable a partire da size e page
        Pageable pageable = PageRequest.of(page, size);
        return pizzaRepository.findAll(pageable);
    }
}
