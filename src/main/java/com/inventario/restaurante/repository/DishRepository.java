package com.inventario.restaurante.repository;


import com.inventario.restaurante.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
    
}
