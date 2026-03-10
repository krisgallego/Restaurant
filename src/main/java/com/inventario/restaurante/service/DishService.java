package com.inventario.restaurante.service;

import com.inventario.restaurante.dto.DishRequestDTO;
import com.inventario.restaurante.dto.DishResponseDTO;
import com.inventario.restaurante.dto.MessageResponseDTO;
import com.inventario.restaurante.entity.Dish;
import com.inventario.restaurante.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishService {

    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository){
        this.dishRepository = dishRepository;
    }

    public List<DishResponseDTO> getDishes(){

        List<Dish> dishes = dishRepository.findAll();
        List<DishResponseDTO> response = new ArrayList<>();

        for(Dish dish : dishes){

            DishResponseDTO dishResponseDTO = new DishResponseDTO();

            dishResponseDTO.setId(dish.getId());
            dishResponseDTO.setName(dish.getName());
            dishResponseDTO.setType(dish.getType());
            dishResponseDTO.setPrice(dish.getPrice());
            dishResponseDTO.setAvailable(dish.isAvailable());

            response.add(dishResponseDTO);
        }

        return response;
    }

    public DishResponseDTO getDishById(Integer id){

        Dish dish = dishRepository.findById(id).orElse(null);

        if(dish == null){
            return null;
        }

        DishResponseDTO dishResponseDTO = new DishResponseDTO();

        dishResponseDTO.setId(dish.getId());
        dishResponseDTO.setName(dish.getName());
        dishResponseDTO.setType(dish.getType());
        dishResponseDTO.setPrice(dish.getPrice());
        dishResponseDTO.setAvailable(dish.isAvailable());

        return dishResponseDTO;
    }

    public MessageResponseDTO createDish(DishRequestDTO request){

        if(request.getPrice() <= 0){
            return new MessageResponseDTO("El precio debe ser mayor a 0");
        }

        Dish dish = new Dish();

        dish.setName(request.getName());
        dish.setType(request.getType());
        dish.setPrice(request.getPrice());
        dish.setAvailable(request.isAvailable());

        dishRepository.save(dish);

        return new MessageResponseDTO("Plato creado con exito");
    }

    public MessageResponseDTO updateDish(Integer id, DishRequestDTO request){

        Dish dish = dishRepository.findById(id).orElse(null);

        if(dish == null){
            return new MessageResponseDTO("Plato no encontrado");
        }

        dish.setName(request.getName());
        dish.setType(request.getType());
        dish.setPrice(request.getPrice());
        dish.setAvailable(request.isAvailable());

        dishRepository.save(dish);

        return new MessageResponseDTO("Plato actualizado correctamente.");
    }

    public MessageResponseDTO deleteDish(Integer id){

        Dish dish = dishRepository.findById(id).orElse(null);

        if(dish == null){
            return new MessageResponseDTO("Plato no encontrado");
        }
        dishRepository.deleteById(id);

        return new MessageResponseDTO("Plato eliminado con exito");
    }

}