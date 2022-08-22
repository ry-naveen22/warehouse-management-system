package com.wh.managementSystem.controller;

import com.wh.managementSystem.service.InventoryService;
import com.wh.managementSystem.service.ProductInventoryService;
import com.wh.managementSystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class UploadController {

    private InventoryService inventoryService;
    private ProductService productService;

    public UploadController(InventoryService inventoryService, ProductService productService) {
        this.inventoryService = inventoryService;
        this.productService = productService;
    }

    @PostMapping("/uploadInventory")
    public String uploadInventory(@RequestParam("file")MultipartFile file, RedirectAttributes redirAttrs){

        if(file.isEmpty()){
            redirAttrs.addFlashAttribute("inventoryMessage","Please select a file to upload");
            return "redirect:/";
        }
        try{
            inventoryService.save(file.getInputStream());
            redirAttrs.addFlashAttribute("inventoryMessage","Inventory uploaded successfully");
        }catch (IOException | NullPointerException e){
            e.printStackTrace();
            redirAttrs.addFlashAttribute("errorMessage","Inventory upload Failed");
            return "redirect:/error";
        }

        return "redirect:/";

    }

    @PostMapping("/uploadProducts")
    public String uploadProducts(@RequestParam("file")MultipartFile file, RedirectAttributes attributes){

        if(file.isEmpty()){
            attributes.addFlashAttribute("productMessage","Please select a file to upload");
            return "redirect:/";
        }
        try{
            productService.save(file.getInputStream());
            attributes.addFlashAttribute("productMessage","Products uploaded successfully");
        }catch (IOException e){
            e.printStackTrace();
            attributes.addFlashAttribute("errorMessage","Inventory upload Failed");
            return "redirect:/error";
        }

        return "redirect:/";

    }
}
