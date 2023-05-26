package br.edu.ifal.contracts.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class ContractsControllerTest {
    private final MockMvc mockMvc;
    private final String urlTemplate = "/v1/contracts";

    @Autowired
    public ContractsControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    @DisplayName("Should return an empty list when there is no contract")
    void shouldReturnAnEmptyListWhenThereIsNoContract() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(urlTemplate))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    @Test
    @DisplayName("Should return created when a contract is added")
    void shouldReturnCreatedWhenAContractIsAdded() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post(urlTemplate).contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().exists("location"))
                .andExpect(MockMvcResultMatchers.content().string(""));
    }
}