import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import {Destination}from '../_services/locationTransport/destination'
import {LocationService}from '../_services/locationTransport/location.service'


@Component({
  selector: 'app-location-data',
  templateUrl: './location-data.component.html',
  styleUrls: ['./location-data.component.css']
})
export class LocationDataComponent implements OnInit {
  @Input() destination:Destination
  @Output() onDeleted = new EventEmitter<boolean>();
  constructor(public locationService:LocationService) { }

  ngOnInit() {
  }
  async delete(){
    await this.locationService.deleteDestination(this.destination.id)
    this.onDeleted.emit(true);

  }
}
