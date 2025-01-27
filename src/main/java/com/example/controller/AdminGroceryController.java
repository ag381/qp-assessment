package com.example.controller;

import com.example.entity.Grocery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.service.GroceryService;

@RestController
@RequestMapping("/api/admin")
public class AdminGroceryController {

    @Autowired
    GroceryService groceryService;

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("<h2>Hello Admin Welcome to Grocery Application!!!!!</h2>");
    }
    @PostMapping("/addGroceryItem")
    public ResponseEntity<Grocery> addGroceryItem(@RequestBody Grocery grocery){
        return groceryService.addGrocery(grocery);
    }
    @GetMapping("/findGroceryItem")
    public ResponseEntity<Grocery> findGroceryItem(@RequestParam(value = "id", required = true) Long id){
        return groceryService.findGrocery(id);
    }
    @PutMapping("/updateGroceryItem")
    public ResponseEntity<Grocery> updateGroceryItem(@RequestBody Grocery grocery){
        return groceryService.updateGrocery(grocery);
    }
    @DeleteMapping("/deleteGroceryItemById/{id}")
    public ResponseEntity<String> deleteGroceryItem(@PathVariable("id") Long id){
        return groceryService.deleteGrocery(id);
    }
    @PatchMapping("/updateGroceryInventory")
    public ResponseEntity<String> updateGroceryInventory(@RequestParam(value="id", required = true) Long id, @RequestParam(value = "quantity", required = false, defaultValue = "0") Integer quantity) {
        return groceryService.updateInventory(id, quantity);
    }
}
