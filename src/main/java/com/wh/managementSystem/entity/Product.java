package com.wh.managementSystem.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Table(name="Product")
public class Product {
    @Id
    @Column(unique = true,nullable = false)
    private String name;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name="product_name")
    @JsonProperty(value="contain_articles")
    private List<ProductArticle> contain_articles;

    public Product(){

    }
    public Product(String name, List<ProductArticle> contain_articles) {
        this.name = name;
        this.contain_articles = contain_articles;
    }

    public String getName() {
        return name;
    }

    public List<ProductArticle> getContain_articles() {
        return contain_articles;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", contain_articles=" + contain_articles +
                '}';
    }


    public Integer getCount(Map<Integer, Integer> articleMap) {
       List<Integer> countList =  contain_articles.stream()
               .map(productArticle -> (articleMap.get(productArticle.getArt_id())/ productArticle.getAmount_of()))
               .collect(Collectors.toList());
        return countList.stream().min(Comparator.naturalOrder()).get();
    }
}
