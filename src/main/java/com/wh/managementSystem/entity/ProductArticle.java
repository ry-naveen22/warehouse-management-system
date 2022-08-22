package com.wh.managementSystem.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="ProductArticle")
public class ProductArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @JsonProperty(value="art_id")
    private Integer art_id;
    @JsonProperty(value="amount_of")
    private Integer amount_of;

    public ProductArticle(){

    }
    public ProductArticle(Integer art_id, Integer amount_of) {
        this.art_id = art_id;
        this.amount_of = amount_of;
    }

    public Integer getArt_id() {
        return art_id;
    }

    public void setArt_id(Integer art_id) {
        this.art_id = art_id;
    }

    public Integer getAmount_of() {
        return amount_of;
    }

    public void setAmount_of(Integer amount_of) {
        this.amount_of = amount_of;
    }

    @Override
    public String toString() {
        return "ProductArticle{" +
                "art_id=" + art_id +
                ", amount_of=" + amount_of +
                '}';
    }
}
