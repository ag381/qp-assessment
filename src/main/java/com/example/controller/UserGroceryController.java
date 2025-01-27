package com.example.controller;

import com.example.entity.Grocery;
import com.example.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.service.GroceryService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserGroceryController {

    @Autowired
    GroceryService groceryService;

    @GetMapping("/viewAllGroceries")
    public ResponseEntity<List<Grocery>> findAllGroceries(){
        return groceryService.findAllGrocery();
    }
    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestBody Order order){
        return groceryService.placeOrder(order);
    }

}
