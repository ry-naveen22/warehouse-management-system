package com.wh.managementSystem.controller;

import com.wh.managementSystem.service.ProductInventoryService;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    @MockBean
    ProductInventoryService productInventoryService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldReturnProductInventoryDetails() throws Exception {
        Map<String,Integer> mockMap = new HashMap<>();
        mockMap.put("Dinning Chair",4);
        when(productInventoryService.getProductInventory()).thenReturn(mockMap);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attribute("productInventories", Matchers.hasEntry("Dinning Chair",4)));

    }
}
