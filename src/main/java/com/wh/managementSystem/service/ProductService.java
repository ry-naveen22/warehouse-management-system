package com.wh.managementSystem.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wh.managementSystem.entity.InventoryHelper;
import com.wh.managementSystem.entity.ProductsHelper;
import com.wh.managementSystem.repository.ArticleRepository;
import com.wh.managementSystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class ProductService implements UploadService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void save(InputStream inputStream) {

        ProductsHelper products= getProducts(inputStream);
        productRepository.saveAll(products.getProducts());
    }

    private ProductsHelper getProducts(InputStream inputStream) {
        ObjectMapper mapper = new ObjectMapper();
//        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        ProductsHelper products =new ProductsHelper();
        try{
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            products = mapper.readValue(inputStream, ProductsHelper.class);
        }catch (IOException e){
            e.printStackTrace();
        }
        return products;
    }
}
