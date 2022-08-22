package com.wh.managementSystem.service;

import com.wh.managementSystem.entity.Article;
import com.wh.managementSystem.entity.Product;
import com.wh.managementSystem.entity.ProductArticle;
import com.wh.managementSystem.repository.ArticleRepository;
import com.wh.managementSystem.repository.ProductRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductInventoryService {

        private ArticleRepository articleRepository;
        private ProductRepository productRepository;

    public ProductInventoryService(ArticleRepository articleRepository, ProductRepository productRepository) {
        this.articleRepository = articleRepository;
        this.productRepository = productRepository;
    }

    public Map<String,Integer> getProductInventory(){
                List<Article> articles = articleRepository.findAll();
                List<Product> products = productRepository.findAll();

                final Map<Integer,Integer> articleMap = articles.parallelStream()
                        .collect(Collectors.toMap(Article::getArt_id,Article::getStock));

                Map<String,Integer> productInventoryMap = products.stream()
                        .collect(Collectors.toMap(Product::getName,(product-> product.getCount(articleMap))));
                return productInventoryMap;
        }


        public void sellProduct(String productName, Integer qty) {
                Optional<Product> productToSell = productRepository.findById(productName);
                if(productToSell.isPresent()){

                               List<ProductArticle> selectedProductArticles = productToSell.get().getContain_articles();
                               for (ProductArticle article:selectedProductArticles
                                    ) {
                                       Optional<Article> inventoryArticle = Optional.ofNullable(articleRepository.findById(article.getArt_id())
                                               .orElseThrow(() -> new ObjectNotFoundException(article.getArt_id(),"Article")));
                                       Article productArticle = inventoryArticle.get();
                                               Integer stock = productArticle.getStock() - (article.getAmount_of()*qty);
                                       productArticle.setStock(stock);
                                       articleRepository.save(productArticle);
                               }

                       }

        }
}
