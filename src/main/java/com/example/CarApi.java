package com.example;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/api/v1/cars")
public class CarApi {

    @Autowired
    CarDetailRepository carDetailRepository;

    @Operation(summary = "Get All Cars", tags="Cars")
    @GetMapping
    public List<CarDetail> getAllCars() {
        return carDetailRepository.findAll();
    }

    @Operation(summary = "Get Cars By Brand", tags="Cars")
    @GetMapping("/brand/{brand}")
    public List<CarDetail> getCarsByBrand(@PathVariable String brand) {
        return carDetailRepository.findByBrand(brand);
    }

    @Operation(summary = "Get Cars By Horse Power", tags="Cars")
    @GetMapping("/horsepower/{horsePower}")
    public List<CarDetail> getCarsByHorsePower(@PathVariable Integer horsePower) {
        return carDetailRepository.findByHorsePowerGreaterThanEqual(horsePower);
    }

    @Operation(summary = "Create Car", tags="Cars")
    @PostMapping
    public ResponseEntity createCar(@RequestBody CarDetail carDetail) {
        carDetailRepository.save(carDetail);
        return ResponseEntity.ok().build();
    }
}
