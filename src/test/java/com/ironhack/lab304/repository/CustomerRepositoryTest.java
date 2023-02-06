package com.ironhack.lab304.repository;

import com.ironhack.lab304.model.Customer;
import com.ironhack.lab304.model.CustomerStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRepositoryTest {
    private Customer testCustomer;
    @Autowired
    CustomerRepository customerRepository;
    @BeforeEach
    void setUp() {
        testCustomer=new Customer("Xavi Barrachina", CustomerStatus.SILVER);
    }

    @AfterEach
    void tearDown() {
        customerRepository.deleteById(testCustomer.getId());
    }
    @Test
    public void createCustomer_validCustomer_addedToDatabase(){

        customerRepository.save(testCustomer);

        Optional<Customer> foundCostumer = customerRepository.findById(testCustomer.getId());
        assertTrue(foundCostumer.isPresent());
        assertEquals(testCustomer.getName(), foundCostumer.get().getName());
    }
    @Test
    public void findByStatus(){
        List<Customer> foundConsumer = customerRepository.findByStatus(CustomerStatus.NONE);
        assertEquals(1, foundConsumer.size());
        assertEquals(testCustomer, foundConsumer.get(0));

        foundConsumer = customerRepository.findByStatus(CustomerStatus.SILVER);
        assertEquals(1, foundConsumer.size());
        assertEquals(testCustomer, foundConsumer.get(0));

        foundConsumer = customerRepository.findByStatus(CustomerStatus.GOLD);
        assertEquals(1, foundConsumer.size());
        assertEquals(testCustomer, foundConsumer.get(0));
    }


}