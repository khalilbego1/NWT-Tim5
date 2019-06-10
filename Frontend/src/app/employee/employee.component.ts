import {Component, OnInit} from '@angular/core';
import {Destination} from '../_services/locationTransport/destination'
import {City} from '../_services/locationTransport/city'
import {Region} from '../_services/locationTransport/region'
import {Country} from '../_services/locationTransport/country'
import {LocationService} from '../_services/locationTransport/location.service'
import {TransportService} from '../_services/locationTransport/transport.service'
import {TransportType} from "../_services/locationTransport/transportType";
import {Transport} from "../_services/locationTransport/transport";


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
    isTransportCollapsed: boolean = true;
    isTransportationTypeCollapsed: boolean = true;

    destinations: Destination[];
    cities: City[];
    regions: Region[];
    countries: Country[];

    selectedCountry: Country = new Country();
    selectedRegion: Region = new Region();
    selectedCity: City = new City();
    selectedTransport: Transport = new Transport();

    TransportTypeData: TransportType[];
    TransportData: Transport[];
    selectedTransportType: TransportType = new TransportType();
    newTransportation: Transport = new Transport();
    newTransportationType: TransportType = new TransportType();

    constructor(public locationService: LocationService, public transportService: TransportService) {
    }

    ngOnInit() {

        this.destinations = [];
        this.cities = [];
        this.regions = [];
        this.countries = [];
        this.TransportData = [];
        this.TransportTypeData = [];
        this.getDestinations();
        this.getCities();
        this.getRegions();
        this.getCountries();
        this.getTransportTypes();
        this.getTransportations();
    }

    async getDestinations() {
        const data = await this.locationService.allDestinations();
        if (data != undefined)
            this.destinations = data;
        else
            this.destinations = [];
    }

    async getCities() {
        const data = await this.locationService.allCities();
        if (data != undefined) {
            this.cities = data;
            this.selectedCity = this.cities[0];
        } else
            this.cities = [];
    }

    async getRegions() {
        const data = await this.locationService.allRegions();
        if (data != undefined) {
            this.regions = data;
            this.selectedRegion = this.regions[0];
        } else
            this.regions = [];
    }

    async getCountries() {
        const data = await this.locationService.allCountries();
        if (data != undefined) {
            this.countries = data;
            this.selectedCountry = this.countries[0];
        } else
            this.countries = [];
        console.log(this.countries)
    }

    async getTransportTypes() {
        const data = await this.transportService.allTransportTypes();
        if (data != undefined)
            this.TransportTypeData = data;
        else
            this.TransportTypeData = [];
    }

    async getTransportations() {
        const data = await this.transportService.allTransport();
        if (data != undefined) {
            this.TransportData = data;
            this.selectedTransport = this.TransportData[0];
        } else
            this.TransportData = [];
        console.log(this.TransportData);
    }

    async createDestination(dest: Destination) {
        await this.locationService.addDestination(dest);
        this.getDestinations();
    }

    async createCity(city: City) {
        await this.locationService.addCity(city);
        this.getCities()
    }

    async createRegion(region: Region) {
        await this.locationService.addRegion(region);
        this.getRegions();
    }

    async createCountry(country: Country) {
        await this.locationService.addCountry(country);
        this.getCountries();
    }

    async createNewTransport() {
        await this.transportService.addTransport(this.newTransportation);
        this.getTransportations();
    }

    async createNewTransportType() {
        await this.transportService.addTransportType(this.newTransportationType);
        this.getTransportTypes();
    }

    setCountry(country: any) {
        this.selectedCountry = country;
    }

    setRegion(region: any) {
        this.selectedRegion = region;
    }

    setCity(city: any) {
        this.selectedCity = city;
    }

    submitCountry(event: any) {
        let country: Country = new Country();
        country.name = event.target.name.value;
        this.createCountry(country);
        this.isCountryCollapsed = true;
    }

    submitRegion(event: any) {
        let region: Region = new Region();
        region.name = event.target.name.value;
        region.country = this.selectedCountry;
        this.createRegion(region);
        this.isRegionCollapsed = true;
    }

    submitCity(event: any) {
        let city: City = new City();
        city.name = event.target.name.value;
        city.region = this.selectedRegion;
        this.createCity(city);
        this.isCityCollapsed = true;
    }

    submitDestination(event: any) {
        let destination: Destination = new Destination();
        destination.name = event.target.name.value;
        destination.city = this.selectedCity;
        this.createDestination(destination);
        this.isDestinationCollapsed = true;
    }

    onDeletedDest(deleted: boolean) {
        if (deleted) this.getDestinations();
    }

    onDeleteTransport(deleted: boolean) {
        if (deleted) this.getTransportations();
    }

    setTransportType(type: any) {
        console.log(type);
        this.selectedTransportType = type;
    }

    submitTransportation(event: any) {
        this.newTransportation.name = event.target.name.value;
        this.newTransportation.transportType = this.selectedTransportType;
        console.log(this.newTransportation);
        this.createNewTransport();
        this.isTransportCollapsed = true;
    }

    submitTransportationType(event: any) {
        this.newTransportationType.name = event.target.name.value;
        this.createNewTransportType();
        this.isTransportationTypeCollapsed = true;
    }
}
