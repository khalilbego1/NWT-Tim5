package com.nwt.locationTransport.Controllers;

import com.nwt.locationTransport.Entities.City;
import com.nwt.locationTransport.Entities.Country;
import com.nwt.locationTransport.Entities.Destination;
import com.nwt.locationTransport.Entities.Region;
import com.nwt.locationTransport.Repositories.CityRepo;
import com.nwt.locationTransport.Repositories.CountryRepo;
import com.nwt.locationTransport.Repositories.DestinationRepo;
import com.nwt.locationTransport.Repositories.RegionRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/getAll")
    public Iterable<Destination>getAllDestinations(){
        Iterable<Destination> destinations =destinationRepo.findAll();
        return destinations;
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
    @PostMapping("/createDestination")
    public void createDestination(@RequestBody Destination destination)throws URISyntaxException {
        destinationRepo.save(destination);
    }
    @PostMapping("/createCity")
    public void createCity(@RequestBody City city)throws URISyntaxException {
        cityRepo.save(city);
    }
    @PostMapping("/createRegion")
    public void createRegion(@RequestBody Region region)throws URISyntaxException {
        regionRepo.save(region);
    }
    @PostMapping("/createCountry")
    public void createCountry(@RequestBody Country country)throws URISyntaxException {
        countryRepo.save(country);
    }


}
