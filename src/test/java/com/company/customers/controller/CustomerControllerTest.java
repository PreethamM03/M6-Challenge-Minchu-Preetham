package com.company.customers.controller;

import com.company.customers.model.Customer;
import com.company.customers.respository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    private List<Customer> customerList = new ArrayList<>();

    @MockBean
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        // Standard set up method for instantiating test objects
        // Don't have to do anything special for mockMvc since it's Autowired
    }


    @Test
    public void getCustomersShouldReturnOk() throws Exception {
        mockMvc.perform(
                get("/customers"))
                .andExpect(status().isOk());
    }

    @Test
    public void getCustomerByIdShouldReturnOk() throws Exception {
        mockMvc.perform(
                get("/customers/1"))
                .andExpect(status().isOk());
    }


    @Test
    public void getCustomersByStateShouldReturnOk() throws Exception {
        mockMvc.perform(
                get("/customers/state/Virginia"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void postCustomerShouldAddNewCustomerAndReturnCreated() throws Exception {
        String inputJson = mapper.writeValueAsString(new Customer());
        mockMvc.perform(
                        post("/customers")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }
    @Test
    void putCustomerShouldAddNewCustomerAndReturnCreated() throws Exception {
        String inputJson = mapper.writeValueAsString(new Customer());
        mockMvc.perform(
                        put("/customers")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(204));
    }
    @Test
    void deleteCustomerShouldAddNewCustomerAndReturnCreated() throws Exception {
        String inputJson = mapper.writeValueAsString(new Customer());
        mockMvc.perform(
                        delete("/customers/1"))
                .andExpect(status().is(204));
    }
}