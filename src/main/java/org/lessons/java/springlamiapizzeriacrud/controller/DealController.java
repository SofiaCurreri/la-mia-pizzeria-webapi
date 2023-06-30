package org.lessons.java.springlamiapizzeriacrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/deals")
public class DealController {

    @GetMapping("/create")
    public String create() {
        return "createDeal";
    }
}
