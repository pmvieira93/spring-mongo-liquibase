package com.example;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
@Repository
public interface CarDetailRepository extends MongoRepository<CarDetail, String> {

    @Query("{ 'brand' : ?0 }")
    List<CarDetail> findByBrand(String brand);

    //@Query
    List<CarDetail> findByHorsePowerGreaterThanEqual(Integer horsePower);
}
