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
    @CrossOrigin
    @GetMapping("/")
    public Iterable<Transportation> getAllTransportation() {
        Iterable<Transportation> transportation = transportationRepo.findAll();
        return transportation;
    }
    @CrossOrigin
    @GetMapping("/{id}")
    public Transportation getTransportationById(@PathVariable Integer id) {
        Transportation transportation = transportationRepo.findById(id).get();
        return transportation;
    }
    @CrossOrigin
    @GetMapping("/oftype/{id}")
    public Iterable<Transportation> getAllTransportationofType(@PathVariable Integer id) {
        Iterable<Transportation> transportation = transportationRepo.findAllByTransportationType(typeRepo.getById(id));
        return transportation;
    }
    @CrossOrigin
    @PostMapping("/")
    public Transportation CreateTransport(@RequestBody Transportation transportation) throws URISyntaxException, DataAccessException, DataFormatException, DataIntegrityViolationException {

        transportationRepo.save(transportation);
        return transportation;
    }
    @CrossOrigin
    @PutMapping("/")
    public Transportation UpdateTransport(@RequestBody Transportation transportation) throws URISyntaxException, DataAccessException, DataFormatException, DataIntegrityViolationException {

        transportationRepo.save(transportation);
        return transportation;
    }
    @CrossOrigin
    @DeleteMapping("/{id}")
    public Transportation DeleteTransport(@PathVariable Integer id) {
        Transportation transportation =transportationRepo.findById(id).get();
        transportationRepo.deleteById(id);
        return transportation;
    }
    @CrossOrigin
    @GetMapping("/types")
    public Iterable<TransportationType> getAllTransportationTypes() {
        Iterable<TransportationType> transportationTypes = typeRepo.findAll();
        return transportationTypes;
    }
    @CrossOrigin
    @GetMapping("types/{id}")
    public TransportationType getTransportationTypeById(@PathVariable Integer id) {
        TransportationType transportation = typeRepo.findById(id).get();
        return transportation;
    }
    @CrossOrigin
    @PostMapping("/types")
    public TransportationType CreateTransportType(@RequestBody TransportationType type) throws URISyntaxException, DataAccessException, DataFormatException, DataIntegrityViolationException {
        typeRepo.save(type);
        return type;
    }
    @CrossOrigin
    @DeleteMapping("/types/{id}")
    public TransportationType DeleteTransportType(@PathVariable Integer id) {
        TransportationType type = typeRepo.findById(id).get();
        typeRepo.deleteById(id);
        return type;
    }

}
