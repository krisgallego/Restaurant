package com.inventario.restaurante.controller;

import com.inventario.restaurante.dto.DishRequestDTO;
import com.inventario.restaurante.dto.DishResponseDTO;
import com.inventario.restaurante.dto.MessageResponseDTO;
import com.inventario.restaurante.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService){
        this.dishService = dishService;
    }

    @GetMapping
    public ResponseEntity<List<DishResponseDTO>> getDishes() {
        try {
            List<DishResponseDTO> response = dishService.getDishes();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishResponseDTO> getDish(@PathVariable Integer id) {
        try {
            DishResponseDTO response = dishService.getDishById(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<MessageResponseDTO> createDish(@RequestBody DishRequestDTO request) {
        try {
            MessageResponseDTO response = dishService.createDish(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponseDTO("Error al crear el plato"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> updateDish(@PathVariable Integer id,
                                                         @RequestBody DishRequestDTO request) {
        try {
            MessageResponseDTO response = dishService.updateDish(id, request);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponseDTO("Error al actualizar el plato"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> deleteDish(@PathVariable Integer id) {
        try {
            MessageResponseDTO response = dishService.deleteDish(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponseDTO("Error al elominar el plato"));
        }
    }
}