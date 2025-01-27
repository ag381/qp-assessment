package com.example.dao;

import com.example.entity.Grocery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryDAO extends JpaRepository<Grocery, Long> {
    @Modifying
    @Transactional
    @Query("update Grocery g set g.inventory=:inventory where g.id=:id")
    void changeInventory(@Param("id") Long id, @Param("inventory") Integer inventory);

    Integer findInventory(@Param("id") Long id);
}
