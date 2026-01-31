package com.aikoder.spring.swagger;

import com.example.car.model.Car;
import com.example.car.model.Engine;
import com.example.car.model.Tire;
import com.example.car.model.Wheel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.annotation.Nonnull;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceSliceTest {

    private CarService carService;

    @BeforeEach
    void setup() {
        carService = new CarService();
    }

    @Test
    void testGetAllNewCars_initialData() {
        @Nonnull List<Car> cars = carService.getAllNewCars();

        assertNotNull(cars, "Car list should not be null");
        assertEquals(10, cars.size(), "Initial car store should have 10 cars");

        // Optional: check first car fields
        Car first = cars.get(0);
        assertNotNull(first.getMake());
        assertNotNull(first.getModel());
        assertNotNull(first.getEngine());
        assertNotNull(first.getWheels());
        assertNotNull(first.getTires());
        assertEquals(4, first.getWheels().size());
        assertEquals(4, first.getTires().size());
    }

    @Test
    void testSaveCar_addsNewCar() {
        Car newCar = createMockCar("TestMake", "TestModel", "TestEngine");
        carService.saveCar(newCar);

        List<Car> cars = carService.getAllNewCars();
        assertEquals(11, cars.size(), "After adding, total cars should be 11");
        assertTrue(cars.stream().anyMatch(c -> c.getMake().equals("TestMake")), "New car should be in list");
    }

    @Test
    void testSearchCars_byMake() {
