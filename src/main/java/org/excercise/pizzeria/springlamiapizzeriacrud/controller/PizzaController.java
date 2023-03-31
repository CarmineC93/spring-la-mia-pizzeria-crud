package org.excercise.pizzeria.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.excercise.pizzeria.springlamiapizzeriacrud.model.Pizza;
import org.excercise.pizzeria.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.naming.Binding;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

        @Autowired
        private PizzaRepository pizzaRepository;

        @GetMapping
        //uso Option perchè il parametro potrà esserci come non esserci
        public String index(Model model, @RequestParam(name = "q") Optional<String> keyword) {
            List<Pizza> pizzas;
            //se il parametro non arriva visualizerà tutto l'elenco
            if(keyword.isEmpty()){
               pizzas = pizzaRepository.findAll(Sort.by("name"));
                model.addAttribute("list", pizzas);
            }
            //se invece viene passato un parametro, verrà estratto e fatta partire una query con il metodo repository
            else{
                pizzas = pizzaRepository.findByNameContainingIgnoreCase(keyword.get());
                //se ci saranno parametri di ricerca collego il parametro passato nell'input al model
                model.addAttribute("keyword", keyword.get());
            }
            model.addAttribute("list", pizzas);
            return "/pizzas/index";
        }

        @GetMapping("/{pizzaId}")
        public String show(@PathVariable("pizzaId") Integer id, Model model){
            Optional<Pizza> result = pizzaRepository.findById(id);
            if(result.isPresent()){
                model.addAttribute("pizza", result.get());
                return "/pizzas/show";
            }else{
                //se l'id chiamato non esiste lancerà un'eccezzione
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }

        @GetMapping("/create")
        public String create(Model model){
            model.addAttribute("pizza", new Pizza());
            return "/pizzas/create";
        }

        @PostMapping("/create")
        public String doCreate(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult br){

            if(br.hasErrors()){
                return "/pizzas/create";
            }

            //per avere più controllo sul nuovo elemento, specifico i campi che dovranno comporlo
            Pizza pizzaToSave = new Pizza();
            pizzaToSave.setName(formPizza.getName());
            pizzaToSave.setDescription(formPizza.getDescription());
            pizzaToSave.setPrice(formPizza.getPrice());

            //per far persistere il nuovo elemento
            pizzaRepository.save(pizzaToSave);
            return "redirect:/pizzas";
        }

    }

