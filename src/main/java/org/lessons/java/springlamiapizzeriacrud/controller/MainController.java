package org.lessons.java.springlamiapizzeriacrud.controller;

import org.lessons.java.springlamiapizzeriacrud.model.Pizza;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pizza")
public class MainController {

    private List<Pizza> getMenu(){
        List<Pizza> menu = new ArrayList<>();
        menu.add(new Pizza("Margherita", "mozzarella, pomodoro, basilico", "https://images.pexels.com/photos/16890470/pexels-photo-16890470/free-photo-of-pizza-margherita-con-lievito-madre-con-basilico-fresco-tritato.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", new BigDecimal("6.50")));
        menu.add(new Pizza("Mortadella e Burrata", "burrata, mortadella, pesto di pistacchi", "https://www.cuochemabuone.it/wp-content/uploads/2022/01/pizza-con-mortadella-e-pistacchi.jpg", new BigDecimal("10.00")));
        menu.add(new Pizza("Crudo e bufala", "mozzarella di bufala, pomodoro, crudo di Parma", "https://gastronomiailcapriccio.it/wp-content/uploads/2022/09/Pizza-fatta-in-casa-1-scaled-1.jpg", new BigDecimal("8.50")));
        menu.add(new Pizza("Salamino", "mozzarella, pomodoro, salamino piccante", "https://images.pexels.com/photos/4773769/pexels-photo-4773769.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", new BigDecimal("7.50")));

        return menu;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("menu", getMenu());
        return "index";
    }
}
