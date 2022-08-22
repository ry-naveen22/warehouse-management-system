package com.wh.managementSystem.repository;

import com.wh.managementSystem.entity.Article;
import com.wh.managementSystem.entity.Product;
import com.wh.managementSystem.entity.ProductArticle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.*;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldSaveProduct(){
        Product product = productRepository.save(getProduct());
        assertNotNull(product);
    }


    private Product getProduct() {

        ProductArticle pa1 = new ProductArticle(1,4);
		ProductArticle pa2 = new ProductArticle(2,8);

		List<ProductArticle> pas1 = Arrays.asList(pa1,pa2);
        return new Product("Dinning Chair",pas1);
    }
}
