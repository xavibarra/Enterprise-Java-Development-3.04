package com.ironhack.lab304.repository;

import com.ironhack.lab304.model.Flight;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FlightRepositoryTest {

    private Flight testFlight;
    @Autowired
    FlightRepository flightRepository;
    @BeforeEach
    void setUp() {
        testFlight=new Flight("800-88",  54851851, "null");
    }

    @AfterEach
    void tearDown() {
        flightRepository.deleteAll();
    }

    @Test
    public void findByNumber(){
        flightRepository.save(testFlight);

        List<Flight> found = flightRepository.findByNumber(testFlight.getNumber());
        assertEquals(1, found.size());
        assertEquals(testFlight, found.get(0));
    }
    @Test
    void findByMileageGreaterThan_ValidMileage_FlightFound() {
        List<Flight> found = flightRepository.findByMileageGreaterThan(500L);
        assertEquals(2, found.size());
        assertEquals(testFlight, found.get(0));


    }
}