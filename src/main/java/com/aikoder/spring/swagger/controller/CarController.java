package com.aikoder.spring.swagger.controller;

import com.example.car.model.Car;
import com.example.car.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nonnull;
import java.util.List;

@RestController
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @Operation(summary = "Get 10 random new cars with nested details")
    @GetMapping("/api/cars/new")
    @Nonnull
    public List<Car> getAllNewCars() {
        return carService.getAllNewCars();
    }
}
