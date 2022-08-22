package com.wh.managementSystem.controller;

import com.wh.managementSystem.service.ProductInventoryService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderController {

    private ProductInventoryService productInventoryService;

    public OrderController(ProductInventoryService productInventoryService) {
        this.productInventoryService = productInventoryService;
    }

    @PostMapping("/sellProduct/{productName}")
    public String sellProduct(@PathVariable("productName") String productName, @RequestParam(value = "qty") Integer qty, RedirectAttributes redirAttrs){
        try{
            productInventoryService.sellProduct(productName,qty);
        }catch(ObjectNotFoundException ex){
            redirAttrs.addFlashAttribute("errorMessage",ex.getMessage()+" "+ex.getEntityName());
            return "redirect:/error";
        }

        return "redirect:/";
    }
}
