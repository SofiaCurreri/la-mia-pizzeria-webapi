package org.lessons.java.springlamiapizzeriacrud.repository;

import org.lessons.java.springlamiapizzeriacrud.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//JpaRepository lavora con Pizza con chiave primaria di tipo Integer
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

    //metodo per cercare la pizza con nome uguale a quello cercato dall' utente
    List<Pizza> findByName(String name);

    //metodo per cercare pizze il cui nome o ingredienti contengano la stringa
    List<Pizza> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);

}
