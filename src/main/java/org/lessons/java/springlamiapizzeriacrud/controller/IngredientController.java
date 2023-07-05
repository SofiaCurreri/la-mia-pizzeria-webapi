package org.lessons.java.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.lessons.java.springlamiapizzeriacrud.model.Ingredient;
import org.lessons.java.springlamiapizzeriacrud.model.Pizza;
import org.lessons.java.springlamiapizzeriacrud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    //controller che gestisce sia lista ingredienti sia form per creare/editare ingrediente
    @GetMapping
    //Optional<Integer> = potrebbe esserci una variabile tipo Integer oppure nulla
    public String index(Model model, @RequestParam("edit") Optional<Integer> ingredientId) {
        //recupero da db tutti ingredienti
        List<Ingredient> ingredientList = ingredientRepository.findAll();
        //passo al model un attributo ingredients con tutti ingredienti
        model.addAttribute("ingredients", ingredientList);

        Ingredient ingredientObj;
        //se ho param ingredientId, allora cerco ingrediente su db
        if (ingredientId.isPresent()) {
            Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientId.get());
            //se è presente valorizzo ingredientObj con l' ingrediente da db
            if (ingredient.isPresent()) {
                ingredientObj = ingredient.get();
            } else {
                //se non presente valorizzo ingredientObj con ingrediente vuoto
                ingredientObj = new Ingredient();
            }
        } else {
            //se non ho param valorizzo ingredientObj con ingrediente vuoto
            ingredientObj = new Ingredient();
        }

        //passo al model attributo ingredientObj per mappare form su un oggetto di tipo Ingredient
        model.addAttribute("ingredientObj", ingredientObj);
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

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        //prima di eliminare ingrediente lo dissocio da tutte le pizze
        Optional<Ingredient> result = ingredientRepository.findById(id);
        if (result.isEmpty()) {
            throw new ResponseStatusException((HttpStatus.NOT_FOUND));
        }
        //ingrediente da eliminare
        Ingredient ingredientToDelete = result.get();
        //per ogni pizza associata all' ingrediente da eliminare
        for (Pizza pizza : ingredientToDelete.getPizza()) {
            pizza.getIngredients().remove(ingredientToDelete);
        }
        ingredientRepository.deleteById(id);
        return "redirect:/ingredients";
    }
}
