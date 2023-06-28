package org.lessons.java.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.lessons.java.springlamiapizzeriacrud.model.Pizza;
import org.lessons.java.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
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
        model.addAttribute("searchInput", searchString == null ? "" : searchString);
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

    //controller che restituisce pagina con form di crazione
    @GetMapping("/create")
    public String create(Model model) {
        //aggiungo al model l' attributo pizza contenente un Pizza vuoto
        model.addAttribute("pizza", new Pizza());
        return "edit"; //template unico per create e edit
    }

    //controller che gestisce la post del form coi dati della pizza
    @PostMapping("/create")
    //ci aspettiamo di ricevere un obj di tipo Pizza, i cui attributi vengono riempiti dai dati inseriti nel form
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //ci sono stati errori
            return "create"; //ritorno template form ma con pizza precaricata
        }
        formPizza.setCreatedAt(LocalDateTime.now());

        //save fa create sql se obj con quella PK non esiste, altrimenti fa update
        pizzaRepository.save(formPizza);

        //se tutto va bene rimando alla lista delle pizze
        return "redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        //verificare se esiste pizza con quell' id
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id = " + id + " not found :(");
        }
        model.addAttribute("pizza", result.get());
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id,
                         @Valid @ModelAttribute("pizza") Pizza formPizza,
                         BindingResult bindingResult) {
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id = " + id + " not found :(");
        }
        Pizza pizzaToEdit = result.get(); //vecchia versione pizza
        //nuova versione pizza = formPizza

        //valido formPizza
        if (bindingResult.hasErrors()) {
            return "edit";
        }

        formPizza.setId(pizzaToEdit.getId());
        formPizza.setCreatedAt(pizzaToEdit.getCreatedAt());
        pizzaRepository.save(formPizza);
        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id = " + id + " not found :(");
        }
        Pizza pizzaToDelete = result.get();
        pizzaRepository.delete(pizzaToDelete);
        redirectAttributes.addFlashAttribute("message", "Pizza" + pizzaToDelete.getName() + " deleted!");
        return "redirect:/pizzas";
    }

}
