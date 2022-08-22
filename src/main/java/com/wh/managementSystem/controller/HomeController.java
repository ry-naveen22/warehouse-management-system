package com.wh.managementSystem.controller;

import com.wh.managementSystem.service.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private ProductInventoryService productInventoryService;

    public HomeController(ProductInventoryService productInventoryService) {
        this.productInventoryService = productInventoryService;
    }

    @GetMapping("/")
    public String homepage(Model model){
        model.addAttribute("productInventories",productInventoryService.getProductInventory());
        return "index";
    }
}
