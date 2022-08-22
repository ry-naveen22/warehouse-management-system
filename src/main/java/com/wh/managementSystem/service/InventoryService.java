package com.wh.managementSystem.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wh.managementSystem.entity.Article;
import com.wh.managementSystem.entity.InventoryHelper;
import com.wh.managementSystem.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService implements UploadService{
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public void save(InputStream inputStream) {

        InventoryHelper inventory= getInventory(inputStream);
        if(inventory == null ){
            throw new NullPointerException("Could not read file");
        }
        saveOrUpdateArticle(inventory.getInventory());
    }

    private void saveOrUpdateArticle(List<Article> inventory) {
        for (Article article: inventory) {
            Optional<Article> dbArticle =  articleRepository.findById(article.getArt_id());
            if(dbArticle.isPresent()){
                Integer stock = dbArticle.get().getStock() + article.getStock();
                dbArticle.get().setStock(stock);
                articleRepository.save(dbArticle.get());
            }else{
                articleRepository.save(article);
            }
        }
    }

    private InventoryHelper getInventory(InputStream inputStream) {
        ObjectMapper mapper = new ObjectMapper();
        InventoryHelper inventory =new InventoryHelper();
        try{
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
             inventory = mapper.readValue(inputStream, InventoryHelper.class);
        }catch (IOException e){
            e.printStackTrace();
        }
    return inventory;
    }
}
