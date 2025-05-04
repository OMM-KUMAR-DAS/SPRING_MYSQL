package com.example.SPRING_MYSQL.Service;

import com.example.SPRING_MYSQL.Entity.ProductEntity;
import com.example.SPRING_MYSQL.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    @Autowired
    ProductRepository productRepository;




    //**********************************LOGIC FOR FETCHING ALL PRODUCTS*******************************

    public List<ProductEntity> getAllProducts()
    {
        return productRepository.findAll();
    }


    //*******************************LOGIC FOR FETCHING ONE PRODUCT************************************

    public Optional<ProductEntity> getProduct(Long id)
    {
        return productRepository.findById(id);
    }


    //*********************************LOGIC FOR ADDING PRODUCT******************************************
    public ProductEntity addProduct(ProductEntity productEntity)
    {
              return productRepository.save(productEntity);
    }

    //***********************************LOGIC FOR DELETING PRODUCT**************************************
    public boolean deleteProduct(Long id)
    {
       if(productRepository.findById(id).isPresent())
       {
           productRepository.deleteById(id);
           return true;
       }
       return false;
    }

    //****************************************LOGIC FOR UPDATING PRODUCT*************************************
    public ProductEntity updateProductDetail(ProductEntity productEntity,Long id)
    {

        Optional<ProductEntity> optionalProductEntity= productRepository.findById(id);


        if(optionalProductEntity.isPresent())
        {
            ProductEntity p= optionalProductEntity.get();


            p.setPrice(productEntity.getPrice());
            p.setTitle(productEntity.getTitle());
            p.setQuantity(productEntity.getQuantity());
            p.setUpdatedAt(LocalDate.now());

            System.out.println(p.getTitle());
            System.out.println(p.getQuantity());
            System.out.println(p.getPrice());
            System.out.println(p.getUpdatedAt());


            return  productRepository.save(p);


        }else{
                  return null;
        }
    }

    //***************************************Fetching product By Name*********************************
    public List<ProductEntity> getProductByTitle(String productTitle)
    {
        return productRepository.findByTitle(productTitle);
    }

    //************************************Fetching product Containing Substring**************************
    public List<ProductEntity> getProductContainingSubString(String word)
    {
        return productRepository.findByTitleContaining(word);
    }

    //************************************Fetching product having price less than**************************
    public List<ProductEntity> getPoductLessThen(BigDecimal decimal)
    {
        return productRepository.findByPriceLessThanEqual(decimal);
    }


    //************************************Fetching product having price between**************************
    public List<ProductEntity> getProductInBetween(BigDecimal decimal,BigDecimal decimal2)
    {
        return productRepository.findByPriceBetween(decimal,decimal2);
    }

    //************************************Fetching product having highest quantities**************************
    public List<ProductEntity> getProductWithQuantities()
    {
        return productRepository.findAllByOrderByQuantityDesc();
    }


}
