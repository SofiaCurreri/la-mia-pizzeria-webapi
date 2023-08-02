package org.lessons.java.springlamiapizzeriacrud.service;

import org.lessons.java.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import org.lessons.java.springlamiapizzeriacrud.model.Pizza;
import org.lessons.java.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    //metodo per ottenere lista pizze, filtrata o meno a seconda che ci sia keyword o meno
    public List<Pizza> getAll(Optional<String> keywordOpt) {
        if (keywordOpt.isEmpty()) return pizzaRepository.findAll();
        else return pizzaRepository.findByName(keywordOpt.get());
    }

    //metodo per ottenere dettaglio pizza presa per id, tira un' eccezione se non lo trova
    public Pizza getById(Integer id) throws PizzaNotFoundException {
        Optional<Pizza> pizzaOpt = pizzaRepository.findById(id);
        if (pizzaOpt.isPresent()) return pizzaOpt.get();
        else throw new PizzaNotFoundException("Pizza with id = " + id + " not found :(");
    }

    //metodo per creare e salvare pizza
    public Pizza create(Pizza pizza) {
        //creo pizza da salvare
        Pizza pizzaToPersist = new Pizza();
        //genero timestamp di creazione
        pizzaToPersist.setCreatedAt(LocalDateTime.now());
        //copio campi di pizza che mi interessano
        pizzaToPersist.setName(pizza.getName());
        pizzaToPersist.setDescription(pizza.getDescription());
        pizzaToPersist.setUrlPhoto(pizza.getUrlPhoto());
        pizzaToPersist.setPrice(pizza.getPrice());
        pizzaToPersist.setIngredients(pizza.getIngredients());

        return pizzaRepository.save(pizzaToPersist);
    }
    

}
