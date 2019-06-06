import {Component, OnInit} from '@angular/core';
import {Destination}from '../_services/locationTransport/destination'
import {City}from '../_services/locationTransport/city'
import {Region}from '../_services/locationTransport/region'
import {Country}from '../_services/locationTransport/country'
import {LocationService}from '../_services/locationTransport/location.service'


@Component({
    selector: 'app-employee',
    templateUrl: './employee.component.html',
    styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

    isCountryCollapsed: boolean = true;
    isRegionCollapsed: boolean = true;
    isCityCollapsed: boolean = true;
    isDestinationCollapsed: boolean = true;

    destinations:Destination[];
    cities:City[];
    regions:Region[];
    countries:Country[];

    selectedCountry:Country= new Country();
    selectedRegion:Region = new Region();
    selectedCity:City = new City();


    constructor(public locationService:LocationService) {
    }

    ngOnInit() {

        this.destinations=[];
        this.cities =[];
        this.regions =[];
        this.countries=[];
        this.getDestinations();
        this.getCities();
        this.getRegions();
        this.getCountries();

    }

async getDestinations(){
    const data = await this.locationService.allDestinations();
    if (data != undefined )
        this.destinations=data;
    else
        this.destinations=[];
}
async getCities(){
    const data = await this.locationService.allCities();
    if (data != undefined )
        {this.cities=data;
            this.selectedCity=this.cities[0];
        }
    else
        this.cities=[];
}
async getRegions(){
    const data = await this.locationService.allRegions();
    if (data != undefined )
        {this.regions=data;
            this.selectedRegion=this.regions[0];
        }
    else
        this.regions=[];
}
async getCountries(){
    const data = await this.locationService.allCountries();
    if (data != undefined )
        {this.countries=data;
            this.selectedCountry=this.countries[0];
        }
    else
        this.countries=[];
    console.log(this.countries)
}
async createDestination(dest:Destination){
    await this.locationService.addDestination(dest);
    this.getDestinations();
}
async createCity(city:City){
    await this.locationService.addCity(city);
    this.getCities()
}
async createRegion(region:Region){
    await this.locationService.addRegion(region);
    this.getRegions();
}
async createCountry(country:Country){
    await this.locationService.addCountry(country);
    this.getCountries();
}


setCountry(country:any){
    this.selectedCountry =country;
}
setRegion(region:any){
    this.selectedRegion=region;
}
setCity(city:any){
    this.selectedCity=city;
}

submitCountry(event:any){
    let country:Country = new Country();
    country.name = event.target.name.value;
    this.createCountry(country);
}
submitRegion(event:any){
    let region:Region = new Region();
    region.name = event.target.name.value;
    region.country=this.selectedCountry;
    this.createRegion(region);
}
submitCity(event:any){
    let city:City = new City();
    city.name = event.target.name.value;
    city.region=this.selectedRegion;
    this.createCity(city);
}
submitDestination(event:any){
    let destination:Destination = new Destination();
    destination.name = event.target.name.value;
    destination.city=this.selectedCity;
    this.createDestination(destination);
}

}
