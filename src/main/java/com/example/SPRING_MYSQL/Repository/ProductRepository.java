package com.example.SPRING_MYSQL.Repository;

import com.example.SPRING_MYSQL.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {


    //method for fetching product by title

    List<ProductEntity> findByTitle(String productTitle);


    //method for fetching product containing substring

    List<ProductEntity> findByTitleContaining(String word);


    //method for fetching product less than price

    List<ProductEntity> findByPriceLessThanEqual(BigDecimal decimal);


    //method for fetching data btw price range

    List<ProductEntity> findByPriceBetween(BigDecimal startingrange,BigDecimal endingrange);

    //method for fetching data with highest quanties

    List<ProductEntity> findAllByOrderByQuantityDesc();
}
