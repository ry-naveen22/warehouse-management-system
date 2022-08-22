package com.wh.managementSystem.controller;

import com.wh.managementSystem.service.ProductInventoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @MockBean
    private ProductInventoryService productInventoryService;
    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldSellProduct() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/sellProduct/Dinning Chair").param("qty","1"))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"));
    }
}
