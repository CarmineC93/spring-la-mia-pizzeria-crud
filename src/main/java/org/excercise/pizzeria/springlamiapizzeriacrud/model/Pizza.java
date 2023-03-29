package org.excercise.pizzeria.springlamiapizzeriacrud.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "pizzas")
public class Pizza {

    //ATTRIBUTES / COLUMNS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @Column(nullable = false)
    private BigDecimal price;

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
