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
import jdk.nashorn.internal.ir.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.zip.DataFormatException;

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
    @GetMapping("/destination/all")
    public Iterable<Destination>getAllDestinations(){
        Iterable<Destination> destinations =destinationRepo.findAll();
        return destinations;
    }
    @GetMapping("/destination/{id}")
    public Destination getById(@PathVariable Integer id){
        Destination destination =destinationRepo.findById(id).get();
        return destination;
    }
    @GetMapping("/city/all")
    public Iterable<Destination>getAllDestinationsByCity(@RequestBody City city){
        Iterable<Destination> destinations =destinationRepo.findDestinationsByCity(city);
        return destinations;
    }
    @GetMapping("/city/inRegion")
    public Iterable<City>getAllCitiesInRegion(@RequestBody Region region){
        Iterable<City> cities =cityRepo.findCitiesByRegion(region);
        return cities;
    }
    @GetMapping("/region/inCountry")
    public Iterable<Region>getAllRegionsInCountry(@RequestBody Country country){
        Iterable<Region> regions =regionRepo.findRegionsByCountry(country);
        return regions;
    }
    @GetMapping("/country/all")
    public Iterable<Country>getAllCountries(){
        Iterable<Country> countries =countryRepo.findAll();
        return countries;
    }
    @PutMapping("/destination/create")
    public Destination createDestination(@RequestBody Destination destination)throws URISyntaxException, DataAccessException, DataFormatException, DataIntegrityViolationException {
        destinationRepo.save(destination);
        return destination;
    }
    @DeleteMapping("/destination/delete/{id}")
    public Destination deleteDestination(@PathVariable Integer id){
        Destination destination = destinationRepo.findById(id).get();
        destinationRepo.deleteById(id);
        sender.send("location.delete",Integer.toString(id) + ";delete");
        return destination;
    }
    @PutMapping("/city/create")
    public City createCity(@RequestBody City city)throws URISyntaxException, DataAccessException, DataFormatException, DataIntegrityViolationException {
        cityRepo.save(city);
        return city;
    }
    @DeleteMapping("/city/delete/{id}")
    public City deleteCity(@PathVariable Integer id){
        City city =cityRepo.findById(id).get();
        cityRepo.deleteById(id);
        return city;
    }
    @PutMapping("/region/create")
    public Region createRegion(@RequestBody Region region)throws URISyntaxException, DataAccessException, DataFormatException, DataIntegrityViolationException {
        regionRepo.save(region);
        return region;
    }
    @DeleteMapping("/region/delete/{id}")
    public Region deleteRegion(@PathVariable Integer id){
        Region region1 = regionRepo.findById(id).get();
        regionRepo.deleteById(id);
        return region1;
    }
    @PutMapping("/country/create")
    public Country createCountry(@RequestBody Country country)throws URISyntaxException, DataAccessException, DataFormatException, DataIntegrityViolationException {
        countryRepo.save(country);
        return country;
    }
    @DeleteMapping("/country/delete/{id}")
    public Country deleteCountry(@PathVariable Integer id){
        Country country = countryRepo.findById(id).get();
        countryRepo.deleteById(id);
        return country;

}


}
