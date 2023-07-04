package org.lessons.java.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.lessons.java.springlamiapizzeriacrud.model.Ingredient;
import org.lessons.java.springlamiapizzeriacrud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    //controller che gestisce sia lista ingredienti sia form per creare/editare ingrediente
    @GetMapping
    public String index(Model model) {
        //recupero da db tutti ingredienti
        List<Ingredient> ingredientList = ingredientRepository.findAll();
        //passo al model un attributo ingredients con tutti ingredienti
        model.addAttribute("ingredients", ingredientList);
        //passo al model attributo ingredientObj per mappare form su un oggetto di tipo Ingredient
        model.addAttribute("ingredientObj", new Ingredient());
        return "indexIngredients";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("ingredientObj") Ingredient formIngredient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepository.findAll());
            return "indexIngredients";
        }
        //salvare ingrediente
        ingredientRepository.save(formIngredient);
        return "redirect:/ingredients";
    }
}
