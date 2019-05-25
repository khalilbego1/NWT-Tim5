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
@RequestMapping("/transportations")
public class TransportationController {
    @Autowired
    private TransportationRepo transportationRepo;
    @Autowired
    private TransportationTypeRepo typeRepo;

    @GetMapping("/")
    public Iterable<Transportation> getAllTransportation() {
        Iterable<Transportation> transportation = transportationRepo.findAll();
        return transportation;
    }
    @GetMapping("/{id}")
    public Transportation getTransportationById(@PathVariable Integer id) {
        Transportation transportation = transportationRepo.findById(id).get();
        return transportation;
    }
    @GetMapping("/oftype/{id}")
    public Iterable<Transportation> getAllTransportationofType(@PathVariable Integer id) {
        Iterable<Transportation> transportation = transportationRepo.findAllByTransportationType(typeRepo.getById(id));
        return transportation;
    }
    @PostMapping("/")
    public Transportation CreateTransport(@RequestBody Transportation transportation) throws URISyntaxException, DataAccessException, DataFormatException, DataIntegrityViolationException {

        transportationRepo.save(transportation);
        return transportation;
    }
    @PutMapping("/")
    public Transportation UpdateTransport(@RequestBody Transportation transportation) throws URISyntaxException, DataAccessException, DataFormatException, DataIntegrityViolationException {

        transportationRepo.save(transportation);
        return transportation;
    }
    @DeleteMapping("/{id}")
    public Transportation DeleteTransport(@PathVariable Integer id) {
        Transportation transportation =transportationRepo.findById(id).get();
        transportationRepo.deleteById(id);
        return transportation;
    }

    @GetMapping("/types")
    public Iterable<TransportationType> getAllTransportationTypes() {
        Iterable<TransportationType> transportationTypes = typeRepo.findAll();
        return transportationTypes;
    }
    @GetMapping("types/{id}")
    public TransportationType getTransportationTypeById(@PathVariable Integer id) {
        TransportationType transportation = typeRepo.findById(id).get();
        return transportation;
    }
    @PostMapping("/types")
    public TransportationType CreateTransportType(@RequestBody TransportationType type) throws URISyntaxException, DataAccessException, DataFormatException, DataIntegrityViolationException {
        typeRepo.save(type);
        return type;
    }
    @DeleteMapping("/types/{id}")
    public TransportationType DeleteTransportType(@PathVariable Integer id) {
        TransportationType type = typeRepo.findById(id).get();
        typeRepo.deleteById(id);
        return type;
    }

}
