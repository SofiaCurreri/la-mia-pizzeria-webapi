package org.lessons.java.springlamiapizzeriacrud.controller;

import org.lessons.java.springlamiapizzeriacrud.model.Pizza;
import org.lessons.java.springlamiapizzeriacrud.model.SpecialDeal;
import org.lessons.java.springlamiapizzeriacrud.repository.PizzaRepository;
import org.lessons.java.springlamiapizzeriacrud.repository.SpecialDealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/deals")
public class DealController {

    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    SpecialDealRepository specialDealRepository;

    @GetMapping("/create")
    public String create(@RequestParam("pizzaId") Integer pizzaId, Model model) {
        //creo nuovo special deal
        SpecialDeal specialDeal = new SpecialDeal();
        //precarico data inizio offerta con data di oggi, poi utente puo cambiarla
        specialDeal.setStartingDate(LocalDate.now());
        //precarico pizza con quella passata come parametro
        Optional<Pizza> pizza = pizzaRepository.findById(pizzaId);
        if (pizza.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id = " + pizzaId + " not found :(");
        }
        specialDeal.setPizza(pizza.get());
        model.addAttribute("specialDeal", specialDeal);
        return "createDeal";
    }
}
