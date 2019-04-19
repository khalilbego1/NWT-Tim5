package com.nwt.locationTransport.Controllers;

import com.nwt.locationTransport.Entities.Transportation;
import com.nwt.locationTransport.Entities.TransportationType;
import com.nwt.locationTransport.Repositories.TransportationRepo;
import com.nwt.locationTransport.Repositories.TransportationTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.rmi.transport.Transport;

import java.net.URISyntaxException;

@RestController
@RequestMapping ("/transport")
public class TransportationController {
    @Autowired
    private TransportationRepo transportationRepo;
    @Autowired
    private TransportationTypeRepo typeRepo;

    @GetMapping("/getAll")
    public Iterable<Transportation>getAllTransportation(){
        Iterable<Transportation> transportation =transportationRepo.findAll();
        return transportation;
    }
    @GetMapping("/getAllOfType")
    public Iterable<Transportation>getAllTransportationofType(@PathVariable Integer id){
        Iterable<Transportation>transportation =transportationRepo.findAllByTransportationType(typeRepo.getById(id));
        return transportation;
    }
    @PutMapping ("/create")
    public void CreateTransport(@RequestBody Transportation transportation)throws URISyntaxException{

        transportationRepo.save(transportation);
    }
    @PutMapping ("/createType")
    public void CreateTransportType(@RequestBody TransportationType type)throws URISyntaxException{
        typeRepo.save(type);
    }
    @DeleteMapping("/delete")
    public void DeleteTransport(@PathVariable Integer id){
        transportationRepo.deleteById(id);
    }
    @DeleteMapping("/delete")
    public void DeleteTransportType(@PathVariable Integer id){
        typeRepo.deleteById(id);
    }

}
