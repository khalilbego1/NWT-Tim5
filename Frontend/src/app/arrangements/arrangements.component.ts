import {Component, OnInit} from '@angular/core';
import {Destination}from '../_services/locationTransport/destination'
import {City}from '../_services/locationTransport/city'
import {Region}from '../_services/locationTransport/region'
import {Country}from '../_services/locationTransport/country'
import {Arrangement} from "../_services/arrangements/arrangement";
import {FilterPipe} from "../FilterPipe/FilterPipe";
import {AdditionalActivity} from "../_services/arrangements/additionalActivity";
import {TransportType} from "../_services/locationTransport/transportType";
import {ArrangementsService} from "../_services/arrangements/arrangements.service";
import {User} from "../_services/user-management/user";

@Component({
    selector: 'app-arrangements',
    templateUrl: './arrangements.component.html',
    styleUrls: ['./arrangements.component.css']
})

export class ArrangementsComponent implements OnInit {

    arrangements: Arrangement[] = [];
    additionalActivity: AdditionalActivity;
    transportType: TransportType;
    user: User;
    destination: Destination;
    queryString:string;


    selectedCountry: Country = new Country();
    selectedRegion: Region = new Region();
    selectedCity: City = new City();


    constructor(public arrangementsService: ArrangementsService) {

        var dest1: Destination = new Destination();
        dest1.id = 1;
        dest1.name = 'dest1';
        var adAct: AdditionalActivity = new AdditionalActivity(1,'test1','desc1');
        var transTy: TransportType = new TransportType(1,'Plane');
        var user:User = new User();
        user.username = 'TestUsername';
        var arrangement: Arrangement = new Arrangement(1,'test1',dest1,adAct,transTy,user);


        this.arrangements.push(arrangement);
        this.arrangements.push(arrangement);
        this.arrangements.push(arrangement);
        arrangement.destination.name='test33';
        this.arrangements.push(arrangement);
    }


    // async loadArrangementById(event)
    // {
    //     this.arrangementsService.loadArrangementById();
    // }

    openAdditionalActivity(event: any)
    {

    }

    setAdditionalActivity(event: any)
    {

    }

    setUser(event: any)
    {

    }

    setTransportType(event: any)
    {

    }

    setLocation(event: any)
    {

    }

    ngOnInit() {
        // this.arrangements = this.arrangementsService.arrangements;
    }

}