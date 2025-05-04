package com.example.SPRING_MYSQL.Controllers;


import com.example.SPRING_MYSQL.Configs.Map_Ans;
import com.example.SPRING_MYSQL.Entity.ProductEntity;
import com.example.SPRING_MYSQL.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    Map_Ans ma;




    //************************************GET ALL PRODUCTS*********************************************

    @GetMapping("/allProducts")
    public Map<String,Object> getAllProducts()
    {
              List<ProductEntity> products= productService.getAllProducts();
        System.out.println(products);
              Map<String,Object> ans= ma.getAns();
              if(!products.isEmpty()){


                  ans.put("status","success");
                  ans.put("message","All products data Fetched");
                  ans.put("data",products);


              }else{
                  ans.put("status","failure");
                  ans.put("message","No products available");
                  ans.put("data",products);

              }

              return ans;
    }


    @GetMapping("product/{id}")
    public Map<String,Object> getProduct(@PathVariable Long id)
    {
         Map<String,Object> ans= ma.getAns();

         Optional<ProductEntity> an=productService.getProduct(id);

         if(an.isPresent())
         {
             ans.put("status","success");
             ans.put("message","Product available");
             ans.put("data",an.get());
         }else{
             ans.put("status","failure");
             ans.put("message","Product Not available");
             ans.put("data",null);
         }
         return ans;

    }

    @PostMapping("/addingProduct")
    public Map<String,Object> addProductDetails(@RequestBody ProductEntity productEntity)
    {

        //System.out.println(productEntity);
        Map<String,Object> ans= ma.getAns();


        ProductEntity p=productService.addProduct(productEntity);
//        System.out.println(p);
        ans.put("status","success");
        ans.put("message","Product added successfully");
        ans.put("Product_Details",p);
        return ans;

    }

    @DeleteMapping("/delete/{id}")
    public Map<String,Object> deleteProduct(@PathVariable Long id)
    {
         Map<String,Object> ans= ma.getAns();
         if(productService.deleteProduct(id))
         {
             ans.put("status","success");
             ans.put("message","Product deleted successfully");

         }else{
             ans.put("status","failure");
             ans.put("message","Product do not exist");
         }
         return ans;
    }

    @PutMapping("/update/{id}")
    public Map<String,Object> updateProduct(@RequestBody ProductEntity productEntity,@PathVariable Long id)
    {
        Map<String,Object> ans= ma.getAns();

        ProductEntity pp= productService.updateProductDetail(productEntity,id);



        if (pp!=null)
        {
            ans.put("status","success");
            ans.put("message","product updated successfully");
            ans.put("updated_data",pp);
        }else {
            ans.put("status","failure");
            ans.put("message","product do not exist");
            ans.put("updated_data",null);
        }
        return ans;

   }

   @GetMapping("/getProductByTitle/{productTitle}")
    public Map<String,Object> getProductByTitle(@PathVariable String productTitle)
   {
       Map<String,Object> ans= ma.getAns();
       List<ProductEntity> list=productService.getProductByTitle(productTitle);
       if(!list.isEmpty())
       {
           ans.put("status","success");
           ans.put("message","Product with given title Fetched successfully");
           ans.put("data",list);
       }else {
           ans.put("status","failure");
           ans.put("message","No products available");
           ans.put("data",null);
       }

       return ans;
   }

   @GetMapping("/getProductsContainingString/{string}")
    public  Map<String,Object> getProductsContainingString(@PathVariable String string)
   {
       Map<String,Object>ans= ma.getAns();
       List<ProductEntity> list=productService.getProductContainingSubString(string);
       if(!list.isEmpty())
       {
           ans.put("status","success");
           ans.put("message","Product with given substring Fetched successfully");
           ans.put("data",list);
       }else {
           ans.put("status","failure");
           ans.put("message","No products available");
           ans.put("data",null);
       }

       return ans;

   }


   @GetMapping("/getProductsCheaperThan/{amount}")
   public Map<String,Object> getPoductLessThen(@PathVariable BigDecimal amount)
   {
       Map<String,Object>ans= ma.getAns();
       List<ProductEntity> list=productService.getPoductLessThen(amount);
       if(!list.isEmpty())
       {
           ans.put("status","success");
           ans.put("message","Product with price less than"+amount+"substring Fetched successfully");
           ans.put("data",list);
       }else {
           ans.put("status","failure");
           ans.put("message","No products available");
           ans.put("data",null);
       }

       return ans;
   }

    @GetMapping("/getProductsInBetween")
    public Map<String,Object> getProductBetween(@RequestParam BigDecimal startingrange,@RequestParam BigDecimal endingrange)
    {
        Map<String,Object>ans= ma.getAns();
        List<ProductEntity> list=productService.getProductInBetween(startingrange,endingrange);
        if(!list.isEmpty())
        {
            ans.put("status","success");
            ans.put("message","Product with price between"+startingrange+"And+"+endingrange+"substring Fetched successfully");
            ans.put("data",list);
        }else {
            ans.put("status","failure");
            ans.put("message","No products available");
            ans.put("data",null);
        }

        return ans;
    }

    @GetMapping("/getProductWithHighestQuantity")
    public Map<String,Object> getProductWithHighestQuantities()
    {
        Map<String,Object>ans= ma.getAns();
        List<ProductEntity> list=productService.getProductWithQuantities();
        if(!list.isEmpty())
        {
            ans.put("status","success");
            ans.put("message","Product with highest quantities  Fetched successfully");
            ans.put("data",list);
        }else {
            ans.put("status","failure");
            ans.put("message","No products available");
            ans.put("data",null);
        }

        return ans;
    }



}
