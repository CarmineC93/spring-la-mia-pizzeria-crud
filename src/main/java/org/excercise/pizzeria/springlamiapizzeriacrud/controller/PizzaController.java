package org.excercise.pizzeria.springlamiapizzeriacrud.controller;

import org.excercise.pizzeria.springlamiapizzeriacrud.model.Pizza;
import org.excercise.pizzeria.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

        @Autowired
        private PizzaRepository pizzaRepository;

        @GetMapping
        public String index(Model model) {
            List<Pizza> pizzas = pizzaRepository.findAll();
            model.addAttribute("list", pizzas);
            return "/pizzas/index";
        }

/*
        @GetMapping("/search")
        public String search(Model model, @RequestParam(name = "q") String keyword){
            List<Pizza>  filteredPizzas = pizzaRepository.findByNameContainingIgnoreCase(keyword);
            model.addAttribute("list", filteredPizzas);
            return "/pizzas/index";
        }
*/


    }

