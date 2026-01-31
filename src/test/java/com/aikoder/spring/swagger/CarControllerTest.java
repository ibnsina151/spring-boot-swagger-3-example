package com.aikoder.spring.swagger;

import com.example.car.model.Car;
import com.example.car.model.Engine;
import com.example.car.model.Tire;
import com.example.car.model.Wheel;
import com.example.car.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Nonnull;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    private List<Car> demoCars;

    @BeforeEach
    void setup() {
        demoCars = List.of(
            createMockCar("Toyota", "Corolla", "1.8L 4-Cylinder"),
            createMockCar("BMW", "3 Series", "2.0L Turbo I4")
        );
    }

    @Test
    void testGetCars_noParams_returnsAll() throws Exception {
        Mockito.when(carService.searchCars(null, null, null)).thenReturn(demoCars);

        mockMvc.perform(get("/api/cars/search")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].make", is("Toyota")))
                .andExpect(jsonPath("$[1].make", is("BMW")));
    }

    @Test
    void testGetCars_filterByMake() throws Exception {
        List<Car> toyotaCars = List.of(createMockCar("Toyota", "Corolla", "1.8L 4-Cylinder"));

        Mockito.when(carService.searchCars(eq("Toyota"), isNull(), isNull())).thenReturn(toyotaCars);

        mockMvc.perform(get("/api/cars/search")
                        .param("make", "Toyota")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].make", is("Toyota")));
    }

    @Test
    void testGetCars_filterByModel() throws Exception {
        List<Car> bmwCars = List.of(createMockCar("BMW", "3 Series", "2.0L Turbo I4"));

        Mockito.when(carService.searchCars(isNull(), eq("3 Series"), isNull())).thenReturn(bmwCars);

        mockMvc.perform(get("/api/cars/search")
                        .param("model", "3 Series")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].model", is("3 Series")));
    }

    @Test
    void testGetCars_filterByEngineType() throws Exception {
        List<Car> engineCars = List.of(createMockCar("BMW", "3 Series", "2.0L Turbo I4"));

        Mockito.when(carService.searchCars(isNull(), isNull(), eq("2.0L Turbo I4"))).thenReturn(engineCars);

        mockMvc.perform(get("/api/cars/search")
                        .param("engineType", "2.0L Turbo I4")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].engine.model", is("2.0L Turbo I4")));
    }

    @Test
    void testGetCars_filterMultipleParams() throws Exception {
        List<Car> filteredCars = List.of(createMockCar("BMW", "3 Series", "2.0L Turbo I4"));

        Mockito.when(carService.searchCars(eq("BMW"), eq("3 Series"), eq("2.0L Turbo I4")))
                .thenReturn(filteredCars);

        mockMvc.perform(get("/api/cars/search")
                        .param("make", "BMW")
                        .param("model", "3 Series")
                        .param("engineType", "2.0L Turbo I4")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].make", is("BMW")))
                .andExpect(jsonPath("$[0].model", is("3 Series")))
                .andExpect(jsonPath("$[0].engine.model", is("2.0L Turbo I4")));
    }

    @Test
    void testGetCars_noMatch_returnsEmpty() throws Exception {
        Mockito.when(carService.searchCars(eq("NonExistent"), isNull(), isNull()))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/cars/search")
                        .param("make", "NonExistent")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    // Utility method to create mock Car
    private Car createMockCar(@Nonnull String make, @Nonnull String model, @Nonnull String engineType) {
        Engine engine = new Engine(engineType, 150);
        List<Wheel> wheels = List.of(
                new Wheel("WheelBrand", 16),
                new Wheel("WheelBrand", 16),
                new Wheel("WheelBrand", 16),
                new Wheel("WheelBrand", 16)
        );
        List<Tire> tires = List.of(
                new Tire("TireType", 32),
                new Tire("TireType", 32),
                new Tire("TireType", 32),
                new Tire("TireType", 32)
        );
        return new Car(make, model, engine, wheels, tires);
    }
}
