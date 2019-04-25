package com.nwt.locationTransport.Controllers;

import com.nwt.locationTransport.Entities.Transportation;
import com.nwt.locationTransport.Entities.TransportationType;
import com.nwt.locationTransport.Repositories.TransportationRepo;
import com.nwt.locationTransport.Repositories.TransportationTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.zip.DataFormatException;

@RestController
@RequestMapping("/transportation")
public class TransportationController {
    @Autowired
    private TransportationRepo transportationRepo;
    @Autowired
    private TransportationTypeRepo typeRepo;

    @GetMapping("/all")
    public Iterable<Transportation> getAllTransportation() {
        Iterable<Transportation> transportation = transportationRepo.findAll();
        return transportation;
    }

    @GetMapping("/allOfType/{id}")
    public Iterable<Transportation> getAllTransportationofType(@PathVariable Integer id) {
        Iterable<Transportation> transportation = transportationRepo.findAllByTransportationType(typeRepo.getById(id));
        return transportation;
    }

    @PutMapping("/create")
    public Transportation CreateTransport(@RequestBody Transportation transportation) throws URISyntaxException, DataAccessException, DataFormatException, DataIntegrityViolationException {

        transportationRepo.save(transportation);
        return transportation;
    }

    @PutMapping("/createType")
    public TransportationType CreateTransportType(@RequestBody TransportationType type) throws URISyntaxException, DataAccessException, DataFormatException, DataIntegrityViolationException {
        typeRepo.save(type);
        return type;
    }

    @DeleteMapping("/delete/{id}")
    public Transportation DeleteTransport(@PathVariable Integer id) {
        Transportation transportation =transportationRepo.findById(id).get();
        transportationRepo.deleteById(id);
        return transportation;
    }

    @DeleteMapping("/delete/type/{id}")
    public TransportationType DeleteTransportType(@PathVariable Integer id) {
        TransportationType type = typeRepo.findById(id).get();
        typeRepo.deleteById(id);
        return type;
    }

}
