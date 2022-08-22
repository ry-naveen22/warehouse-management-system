package com.wh.managementSystem.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Article")

public class Article {

    @Id
    @JsonProperty(value="art_id")
    private Integer art_id;
    @JsonProperty(value="name")
    private String name;
    @JsonProperty(value="stock")
    private Integer stock;

    public Article(){
        
    }
    public Article(Integer art_id, String name, Integer stock) {
        this.art_id = art_id;
        this.name = name;
        this.stock = stock;
    }

    public Integer getArt_id() {
        return art_id;
    }

    public void setArt_id(Integer art_id) {
        this.art_id = art_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Article{" +
                "art_id=" + art_id +
                ", name='" + name + '\'' +
                ", stock=" + stock +
                '}';
    }
}
