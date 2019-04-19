package com.nwt.locationTransport.Controllers;

import com.nwt.locationTransport.Entities.City;
import com.nwt.locationTransport.Entities.Country;
import com.nwt.locationTransport.Entities.Destination;
import com.nwt.locationTransport.Entities.Region;
import com.nwt.locationTransport.Repositories.CityRepo;
import com.nwt.locationTransport.Repositories.CountryRepo;
import com.nwt.locationTransport.Repositories.DestinationRepo;
import com.nwt.locationTransport.Repositories.RegionRepo;
import com.nwt.locationTransport.Services.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Iterator;

@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private CityRepo cityRepo;
    @Autowired
    private CountryRepo countryRepo;
    @Autowired
    private DestinationRepo destinationRepo;
    @Autowired
    private RegionRepo regionRepo;
    @Autowired
    DiscoveryClient discoveryClient;

    private final Sender sender;

    @Autowired
    public LocationController(Sender sender){this.sender=sender;}
    @GetMapping("/getAll")
    public Iterable<Destination>getAllDestinations(){
        Iterable<Destination> destinations =destinationRepo.findAll();
        return destinations;
    }
    @GetMapping("/get")
    public Destination getById(@PathVariable Integer id){
        Destination destination =destinationRepo.findById(id).get();
        return destination;
    }
    @GetMapping("/getAllInCity")
    public Iterable<Destination>getAllDestinationsByCity(@RequestBody City city){
        Iterable<Destination> destinations =destinationRepo.findDestinationsByCity(city);
        return destinations;
    }
    @GetMapping("/getCitiesInRegion")
    public Iterable<City>getAllCitiesInRegion(@RequestBody Region region){
        Iterable<City> cities =cityRepo.findCitiesByRegion(region);
        return cities;
    }
    @GetMapping("/getRegionsInCountry")
    public Iterable<Region>getAllRegionsInCountry(@RequestBody Country country){
        Iterable<Region> regions =regionRepo.findRegionsByCountry(country);
        return regions;
    }
    @GetMapping("/getAllCountries")
    public Iterable<Country>getAllCountries(){
        Iterable<Country> countries =countryRepo.findAll();
        return countries;
    }
    @PutMapping("/createDestination")
    public void createDestination(@RequestBody Destination destination)throws URISyntaxException {
        destinationRepo.save(destination);
    }
    @DeleteMapping("/deleteDestination/{id}")
    public void deleteDestination(@PathVariable Integer id){
        destinationRepo.deleteById(id);
        sender.send("location.delete",Integer.toString(id) + ";delete");

    }
    @PutMapping("/createCity")
    public void createCity(@RequestBody City city)throws URISyntaxException {
        cityRepo.save(city);
    }
    @DeleteMapping("/deleteCity")
    public void deleteCity(@PathVariable Integer id){
        cityRepo.deleteById(id);
    }
    @PutMapping("/createRegion")
    public void createRegion(@RequestBody Region region)throws URISyntaxException {
        regionRepo.save(region);
    }
    @DeleteMapping("/deleteRegion")
    public void deleteRegion(@PathVariable Integer id){
        regionRepo.deleteById(id);
    }
    @PutMapping("/createCountry")
    public void createCountry(@RequestBody Country country)throws URISyntaxException {
        countryRepo.save(country);
    }
    @DeleteMapping("/deleteCountry")
    public void deleteCountry(@PathVariable Integer id){
        countryRepo.deleteById(id);
        sender.send("location.delete",Integer.toString(id) + ";delete");
    }


}
