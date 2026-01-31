package com.aikoder.spring.swagger.service;

import com.example.car.model.Car;
import com.example.car.model.Engine;
import com.example.car.model.Tire;
import com.example.car.model.Wheel;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CarService {

    private final Random random = new Random();

    @Nonnull
    public List<Car> getAllNewCars() {
        List<Car> cars = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {

            Engine engine = new Engine("EngineModel-" + i, 100 + random.nextInt(200));

            List<Wheel> wheels = new ArrayList<>();
            List<Tire> tires = new ArrayList<>();
            for (int j = 1; j <= 4; j++) {
                wheels.add(new Wheel("WheelBrand-" + j, 15 + random.nextInt(5)));
                tires.add(new Tire("TireType-" + j, 30 + random.nextInt(10)));
            }

            Car car = new Car("Car-" + i, engine, wheels, tires);
            cars.add(car);
        }
        return cars;
    }
}
