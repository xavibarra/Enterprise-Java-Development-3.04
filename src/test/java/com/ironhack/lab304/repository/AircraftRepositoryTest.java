package com.ironhack.lab304.repository;

import com.ironhack.lab304.model.Aircraft;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AircraftRepositoryTest {
    private Aircraft testAircraft, testAircraft2;
    @Autowired
    AircraftRepository aircraftRepository;
    @BeforeEach
    void setUp(){
        testAircraft=new Aircraft("Airbus", 400);
        testAircraft2=new Aircraft("Boeing", 560);
    }
    @AfterEach
    void tearDown() {
        aircraftRepository.deleteAll();
    }
    @Test
    public void createAircraft(){
        aircraftRepository.save(testAircraft);
        aircraftRepository.save(testAircraft2);
        //findAll()
        List<Aircraft> aircraftList = aircraftRepository.findAll();
        assertEquals(2, aircraftList.size());

        Optional<Aircraft> foundAircraft= aircraftRepository.findById(testAircraft.getModel());
        assertTrue(foundAircraft.isPresent());
        assertEquals(testAircraft.getModel(), foundAircraft.get().getModel());

    }
    @Test
    void nameLikeBoeing() {
        assertEquals(1, aircraftRepository.findByModelLike("Boeing").size());
        assertEquals(testAircraft, aircraftRepository.findByModelLike("Boeing").get(0));
    }
}