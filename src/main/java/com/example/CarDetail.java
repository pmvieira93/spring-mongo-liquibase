package com.example;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 */
@Document("cardetail_tbl")
public class CarDetail {

    @Id
    private String id;
    private String brand;
    private String model;
    private Double engine;
    private Integer horsePower;

    public CarDetail() {
    }

    public CarDetail(String brand, String model, Double engine, Integer horsePower) {
        this.brand = brand;
        this.model = model;
        this.engine = engine;
        this.horsePower = horsePower;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getEngine() {
        return engine;
    }

    public void setEngine(Double engine) {
        this.engine = engine;
    }

    public Integer getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
    }
}
