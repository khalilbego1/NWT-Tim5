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
    @CrossOrigin
    @GetMapping("/destinations")
    public Iterable<Destination>getAllDestinations(){
        Iterable<Destination> destinations =destinationRepo.findAll();
        return destinations;
    }
    @CrossOrigin
    @GetMapping("/destinations/{id}")
    public Destination getDestinationById(@PathVariable Integer id){
        Destination destination =destinationRepo.findById(id).get();
        return destination;
    }
    @CrossOrigin
    @GetMapping("/destinations/city")
    public Iterable<Destination>getAllDestinationsByCity(@RequestBody City city){
        Iterable<Destination> destinations =destinationRepo.findDestinationsByCity(city);
        return destinations;
    }
    @CrossOrigin
    @PostMapping("/destinations")
    public Destination createDestination(@RequestBody Destination destination)throws URISyntaxException, DataAccessException, DataFormatException, DataIntegrityViolationException {
        destinationRepo.save(destination);
        return destination;
    }
    @CrossOrigin
    @PutMapping("/destinations/{id}")
    public Destination updateDestination(@PathVariable Integer id,@RequestBody Destination destination ){
        Destination old =destinationRepo.findById(id).get();
        old = destination;
        destinationRepo.save(old);
        return old;
    }
    @CrossOrigin
    @DeleteMapping("/destinations/{id}")
    public Destination deleteDestination(@PathVariable Integer id){
        Destination destination = destinationRepo.findById(id).get();
        destinationRepo.deleteById(id);
        sender.send("location.delete",Integer.toString(id) + ";delete");
        return destination;
    }
    @CrossOrigin
    @GetMapping("/cities")
    public Iterable<City>getAllCities(){
        Iterable<City> cities =cityRepo.findAll();
        return cities;
    }
    @CrossOrigin
    @GetMapping("/cities/{id}")
    public City getCityById(@PathVariable Integer id){
        City city =cityRepo.findById(id).get();
        return city;
    }
    @CrossOrigin
    @GetMapping("/cities/region")
    public Iterable<City>getAllCitiesInRegion(@RequestBody Region region){
        Iterable<City> cities =cityRepo.findCitiesByRegion(region);
        return cities;
    }
    @CrossOrigin
    @PostMapping("/cities")
    public City createCity(@RequestBody City city)throws URISyntaxException, DataAccessException, DataFormatException, DataIntegrityViolationException {
        cityRepo.save(city);
        return city;
    }
    @CrossOrigin
    @PutMapping("/cities/{id}")
    public City updateCity(@PathVariable Integer id,@RequestBody City city ){
        City old =cityRepo.findById(id).get();
        old = city;
        cityRepo.save(old);
        return old;
    }
    @CrossOrigin
    @DeleteMapping("/cities/{id}")
    public City deleteCity(@PathVariable Integer id){
        City city =cityRepo.findById(id).get();
        cityRepo.deleteById(id);
        return city;
    }
    @CrossOrigin
    @GetMapping("/regions")
    public Iterable<Region>getAllRegions(){
        Iterable<Region> regions =regionRepo.findAll();
        return regions;
    }
    @CrossOrigin
    @GetMapping("/regions/{id}")
    public Region getRegionById(@PathVariable Integer id){
        Region region =regionRepo.findById(id).get();
        return region;
    }
    @CrossOrigin
    @GetMapping("/regions/country")
    public Iterable<Region>getAllRegionsInCountry(@RequestBody Country country){
        Iterable<Region> regions =regionRepo.findRegionsByCountry(country);
        return regions;
    }
    @CrossOrigin
    @PostMapping("/regions")
    public Region createRegion(@RequestBody Region region)throws URISyntaxException, DataAccessException, DataFormatException, DataIntegrityViolationException {
        regionRepo.save(region);
        return region;
    }
    @CrossOrigin
    @PutMapping("/regions/{id}")
    public Region updateRegion(@PathVariable Integer id,@RequestBody Region region ){
        Region old =regionRepo.findById(id).get();
        old = region;
        regionRepo.save(old);
        return old;
    }
    @CrossOrigin
    @DeleteMapping("/regions/{id}")
    public Region deleteRegion(@PathVariable Integer id){
        Region region1 = regionRepo.findById(id).get();
        regionRepo.deleteById(id);
        return region1;
    }
    @CrossOrigin
    @GetMapping("/countries")
    public Iterable<Country>getAllCountries(){
        Iterable<Country> countries =countryRepo.findAll();
        return countries;
    }
    @CrossOrigin
    @GetMapping("/countries/{id}")
    public Country getCountryById(@PathVariable Integer id){
        Country country =countryRepo.findById(id).get();
        return country;
    }
    @CrossOrigin
    @PostMapping("/countries")
    public Country createCountry(@RequestBody Country country)throws URISyntaxException, DataAccessException, DataFormatException, DataIntegrityViolationException {
        countryRepo.save(country);
        return country;
    }
    @CrossOrigin
    @PutMapping("/countries/{id}")
    public Country updateCountry(@PathVariable Integer id,@RequestBody Country country ){
        Country old =countryRepo.findById(id).get();
        old = country;
        countryRepo.save(old);
        return old;
    }
    @CrossOrigin
    @DeleteMapping("/countries/{id}")
    public Country deleteCountry(@PathVariable Integer id){
        Country country = countryRepo.findById(id).get();
        countryRepo.deleteById(id);
        return country;

}


}
