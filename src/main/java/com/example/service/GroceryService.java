package com.example.service;

import com.example.dao.GroceryDAO;
import com.example.dao.OrderDAO;
import com.example.entity.Grocery;
import com.example.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GroceryService {

    @Autowired
    GroceryDAO groceryDAO;

    @Autowired
    OrderDAO orderDAO;

    public ResponseEntity<List<Grocery>> findAllGrocery(){
        List<Grocery> groceries = groceryDAO.findAll();
        return ResponseEntity.ok(groceries);
    }

    public ResponseEntity<Grocery> addGrocery(Grocery grocery) {
        try{
            groceryDAO.save(grocery);
        }catch(Exception e){
            throw new RuntimeException("Failure in adding new grocery item!!!");
        }
        return ResponseEntity.status(201).body(grocery);
    }
    public ResponseEntity<Grocery> findGrocery(Long id) {
        Optional<Grocery> g = groceryDAO.findById(id);
        if(g.isPresent()){
            return ResponseEntity.status(200).body(g.get());
        }
        else{
            throw new RuntimeException("Grocery Item with id: "+id+" not found!");
        }
    }
    public ResponseEntity<String> deleteGrocery(Long id) {
        Optional<Grocery> g = groceryDAO.findById(id);
        if(g.isPresent()){
            groceryDAO.delete(g.get());
            return ResponseEntity.status(HttpStatus.OK).body("ITEM with id "+id+" deleted successfully!");
        }
        else{
            throw new RuntimeException("Grocery Item with id: "+id+" not found!");
        }
    }

    public ResponseEntity<Grocery> updateGrocery(Grocery grocery){

        if(groceryDAO.findById(grocery.getId()).isPresent()) {
                groceryDAO.deleteById(grocery.getId());
                groceryDAO.save(grocery);
                return ResponseEntity.status(HttpStatus.OK).body(grocery);
            }
        else{
            throw new RuntimeException("Grocery Item not found in DB!");
        }
    }

    public ResponseEntity<String> updateInventory(Long id,Integer quantity) {
        Optional<Grocery> g = groceryDAO.findById(id);
        if(g.isPresent()){
            groceryDAO.changeInventory(id,quantity);
            g = groceryDAO.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Inventory for Item "+id+" updated with "+quantity);
        }
        else{
            throw new RuntimeException("Grocery Item with id: "+id+" not found!");
        }
    }

    public ResponseEntity<String> placeOrder(Order order) {
        Map<Long,Integer> orderItems = order.getOrderItems();
        for(Long i: orderItems.keySet()){
            if(groceryDAO.findById(i).isPresent()){
                int inv= groceryDAO.findInventory(i);
                if(inv>0 && orderItems.get(i)<=inv){
                    groceryDAO.changeInventory(i,inv-orderItems.get(i));
                }else{
                    throw new RuntimeException("Requested Item "+ i +" is out of Stock! Please Reorder");
                }
            }else{
                throw new RuntimeException("Requested Item "+i+" is Incorrect! Please Reorder");
            }
        }
        orderDAO.save(order);
        return ResponseEntity.ok("Order Placed Successfully\n"+order.toString());
    }
}
