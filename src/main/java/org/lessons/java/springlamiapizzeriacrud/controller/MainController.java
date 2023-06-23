package org.lessons.java.springlamiapizzeriacrud.controller;

import org.lessons.java.springlamiapizzeriacrud.model.Pizza;
import org.lessons.java.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pizzas")
public class MainController {
    //PizzaRepository mi dà accesso al db

    @Autowired
    //quando serve pizzaRepository al mio controller, crea da solo un' istanza e me lo passa; equivale ad iniettare la dipendenza da PizzaRepository
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String menu(Model model) {
        List<Pizza> menu = pizzaRepository.findAll();
        model.addAttribute("menu", menu);
        return "index";
    }
}
