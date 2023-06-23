package org.lessons.java.springlamiapizzeriacrud.repository;

import org.lessons.java.springlamiapizzeriacrud.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository lavora con Pizza con chiave primaria di tipo Integer
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
}
