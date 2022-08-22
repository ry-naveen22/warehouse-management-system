package com.wh.managementSystem.controller;

import com.wh.managementSystem.service.InventoryService;
import com.wh.managementSystem.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

import static org.mockito.Mockito.*;

@WebMvcTest(UploadController.class)
public class UploadControllerTest {

    @MockBean
    InventoryService inventoryService;

    @MockBean
    ProductService productService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldUploadInventory() throws Exception {


        MockMultipartFile jsonFile = new MockMultipartFile("file", ""
                , "application/json"
                , "{\"inventory\": \"[{\"art_id\":\"1\",\"name\":\"leg\",\"stock\":\"12\"}]}".getBytes());
        this.mockMvc
                .perform(MockMvcRequestBuilders.multipart("/uploadInventory").file(jsonFile))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"))
                .andExpect(MockMvcResultMatchers.flash().attribute("inventoryMessage","Inventory uploaded successfully"));
    }


    @Test
    void shouldUploadProduct() throws Exception {

        MockMultipartFile jsonFile = new MockMultipartFile("file", ""
                , "application/json"
                , "{\"products\": \"[{\"name\":\"Dinning Chair\",\"contains_articles\":[{\"art_id\":\"1\",\"amount_of\":\"4\"}]}]}".getBytes());
        this.mockMvc
                .perform(MockMvcRequestBuilders.multipart("/uploadProducts").file(jsonFile))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"))
                .andExpect(MockMvcResultMatchers.flash().attribute("productMessage","Products uploaded successfully"));
    }
}
