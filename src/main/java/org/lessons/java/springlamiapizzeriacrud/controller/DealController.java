package org.lessons.java.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.lessons.java.springlamiapizzeriacrud.model.Pizza;
import org.lessons.java.springlamiapizzeriacrud.model.SpecialDeal;
import org.lessons.java.springlamiapizzeriacrud.repository.PizzaRepository;
import org.lessons.java.springlamiapizzeriacrud.repository.SpecialDealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "formDeal";
    }

    @PostMapping("/create")
    //ci aspettiamo di ricevere un obj di tipo SpecialDeal, i cui attributi vengono riempiti dai dati inseriti nel form
    public String store(@Valid @ModelAttribute("specialDeal") SpecialDeal formDeal, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //ci sono stati errori
            return "formDeal"; //ritorno template form ma con special deal precaricata
        }

        //save fa create sql se obj con quella PK non esiste, altrimenti fa update
        specialDealRepository.save(formDeal);

        //se tutto va bene rimando alla pag dettaglio della pizza
        return "redirect:/pizzas/" + formDeal.getPizza().getId();
    }

    @GetMapping("/edit/{id}")
    public String editDeal(@PathVariable Integer id, Model model) {
        //verificare se esiste deal con quell' id
        Optional<SpecialDeal> specialDeal = specialDealRepository.findById(id);
        if (specialDeal.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Special Deal with id = " + id + " not found :(");
        }
        model.addAttribute("specialDeal", specialDeal.get());
        return "formDeal";
    }

    @PostMapping("/edit/{id}")
    public String updateDeal(@PathVariable Integer id,
                             @Valid @ModelAttribute("specialDeal") SpecialDeal formDeal,
                             BindingResult bindingResult) {
        Optional<SpecialDeal> dealToEdit = specialDealRepository.findById(id);
        if (dealToEdit.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Special Deal with id = " + id + " not found :(");
        }

        formDeal.setId(id);
        specialDealRepository.save(formDeal);
        return "redirect:/pizzas/" + formDeal.getPizza().getId();
    }

    @PostMapping("/delete/{id}")
    public String deleteDeal(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<SpecialDeal> dealToDelete = specialDealRepository.findById(id);
        if (dealToDelete.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Special Deal with id = " + id + " not found :(");
        }
        specialDealRepository.delete(dealToDelete.get());
        redirectAttributes.addFlashAttribute("message", "Special Deal " + dealToDelete.get().getTitle() + " deleted!");
        return "redirect:/pizzas/" + dealToDelete.get().getPizza().getId();
    }
}
