import { Component, OnInit } from '@angular/core';
import {UserManagementService} from '../_services/user-management/user-management.service'
import {LoginInput} from '../_services/user-management/loginInput'
import {Router} from "@angular/router"
import * as crypto from 'crypto-js';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(public userManagementService:UserManagementService,private router:Router) { }
  login:LoginInput = new LoginInput()
  ngOnInit() {
  }

  async submitLogin(event: any){
    this.login.username =event.target.username.value;
    this.login.password= event.target.password.value;
    const data =await this.userManagementService.login(this.login);
    console.log(data);
    this.router.navigate((['../admin']))
  }
}