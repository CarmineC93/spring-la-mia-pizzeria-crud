package org.excercise.pizzeria.springlamiapizzeriacrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.math.BigDecimal;
import java.security.PublicKey;

@Entity
@Table(name = "pizzas")
public class Pizza {

    //ATTRIBUTES / COLUMNS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Text can not be empty :(")
    private String name;
    @NotEmpty(message = "Text can not be empty :(")
    @Max(value = 250, message = "The text must be smaller than 250 characters :(")
    @Lob
    private String description;
    @NotNull(message = "Text can not be empty :(")
    @Positive (message = "Price must be greater than zero :(")
    @Column(nullable = false)
    private BigDecimal price;

    //CONTRUCTOR
    public Pizza(){
        super();
    }

    //GETTER & SETTER

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
