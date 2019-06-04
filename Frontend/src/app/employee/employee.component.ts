import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  
  isCountryCollapsed:boolean=true;
  //region
  isRegionCollapsed:boolean=true;
  
  isCityCollapsed:boolean=true;
  isDestinationCollapsed:boolean=true;
  constructor() { }

  ngOnInit() {
  }

}
