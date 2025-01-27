package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "GroceryTAB")
@NamedQuery(name="Grocery.findInventory",query = "select g.inventory from Grocery g where g.id=:id")
public class Grocery {
    @Id
    private Long id;
    private String name;
    private String price;
    private String description;
    private int inventory;

}
