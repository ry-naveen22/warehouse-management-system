package com.wh.managementSystem.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InventoryHelper {

    @JsonProperty(value="inventory")
    private List<Article> inventory;
    public InventoryHelper(){

    }
    public InventoryHelper(List<Article> articles) {
        this.inventory = articles;
    }

    public List<Article> getInventory() {
        return inventory;
    }

    public void setInventory(List<Article> inventory) {
        this.inventory = inventory;
    }
}
