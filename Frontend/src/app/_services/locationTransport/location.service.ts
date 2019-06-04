import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {OktaService} from '../auth/okta.service'
import {Country} from './country'
import {Region} from './region'
import {City} from './city'
import {Destination} from './destination'
import { environment } from '../environment'

//const baseUrl = environment.url+'/travelAgency/user-service'
const baseUrl ='http://localhost:8090/location';
@Injectable({
  providedIn: 'root'
})
export class LocationService {

  constructor(private http: HttpClient, private oktaService: OktaService) { }
  private async request(method: string, url: string, data?: any){
    let headers: HttpHeaders = new HttpHeaders();
    if (this.oktaService.getAccessToken()) {
      const accessToken = this.oktaService.getAccessToken();
      headers = headers.append('Authorization', accessToken.tokenType + ' ' + accessToken.accessToken);
      headers =headers.append('Access-Control-Allow-Origin', 'http://localhost:4200');
    }

    const result = this.http.request(method, url, {
      headers:headers,
      body: data,
      responseType: 'json',
      observe: 'body'
      
    });
    return new Promise<any>((resolve, reject) => {
      result.subscribe(resolve as any, reject as any);
    });
  }
  allDestinations(){
    return this.request('get',baseUrl+'/destinations')
  }
  oneDestination(id:number){
    return this.request('get',baseUrl+'/destinations'+String(id))
  }
  allDestinationsInCity(city:City){
    return this.request('get',baseUrl+'/destinations/city',city)
  }
  addDestination(dest:Destination){
    return this.request('post',baseUrl+'/destinations',dest)
  }
  editDestination(id:number,dest:Destination){
    return this.request('put',baseUrl+'/destinations'+String(id),dest);
  }
  deleteDestination(id:number){
    return this.request('delete',baseUrl+'/destinations'+String(id))
  }


  allCities(){
    return this.request('get',baseUrl+'/cities')
  }
  oneCity(id:number){
    return this.request('get',baseUrl+'/city'+String(id))
  }
  allCitiesInRegion(region:Region){
    return this.request('get',baseUrl+'/cities/region',region)
  }
  addCity(city:City){
    return this.request('post',baseUrl+'/cities',city)
  }
  editCity(id:number,city:City){
    return this.request('put',baseUrl+'/cities'+String(id),city);
  }
  deleteCity(id:number){
    return this.request('delete',baseUrl+'/'+String(id))
  }

  allRegions(){
    return this.request('get',baseUrl+'/regions')
  }
  oneRegion(id:number){
    return this.request('get',baseUrl+'/regions'+String(id))
  }
  allRegionsinCountry(country:Country){
    return this.request('get',baseUrl+'/regions/country',country)
  }
  addRegion(region:Region){
    return this.request('post',baseUrl+'/region',region)
  }
  editRegion(id:number,region:Region){
    return this.request('put',baseUrl+'/region'+String(id),region);
  }
  deleteRegion(id:number){
    return this.request('delete',baseUrl+'/regions'+String(id))
  }

  allCountries(){
    return this.request('get',baseUrl+'/countries')
  }
  oneCountry(id:number){
    return this.request('get',baseUrl+'/countries'+String(id))
  }
  addCountry(country:Country){
    return this.request('post',baseUrl+'/countries',country)
  }
  editCountry(id:number,country:Country){
    return this.request('put',baseUrl+'/countries'+String(id),country);
  }
  deleteCountry(id:number){
    return this.request('delete',baseUrl+'/countries'+String(id))
  }
}
