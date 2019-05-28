import { Component, OnInit } from '@angular/core';
import {UserManagementService} from '../_services/user-management/user-management.service'
import {Role} from '../_services/user-management/role'
import {User} from '../_services/user-management/user'
import {LoginInput} from '../_services/user-management/loginInput'
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})


export class AdminComponent implements OnInit {
  newUser:User=new User();
  newRole:Role = new Role();
  UserData:User[];
  RoleData:Role[];
  selectedRole:Role= new Role();
  constructor(public userManagementService:UserManagementService,  private route: ActivatedRoute,private location: Location) { }

  ngOnInit() {

    const id = +this.route.snapshot.paramMap.get('id');
    console.log(id);
    this.UserData=[];
    this.RoleData=[];
    this.refreshUsers()
    this.refreshRoles();


  }

  async refreshUsers(){
    const data = await this.userManagementService.AllUsers();
    if (data!=undefined && data._embedded!=undefined)
    this.UserData=data._embedded.userEntities;
    else this.UserData=[]
  }
  async refreshRoles(){
    const data = await this.userManagementService.allRoles();
    if (data!=undefined && data._embedded!=undefined)
    this.RoleData=data._embedded.roleEntities;
    else this.RoleData=[]
    this.selectedRole =this.RoleData[0];

  }
  async createNewUser(){
    await this.userManagementService.registration(this.newUser);
    this.refreshUsers();
  }
  async createNewRole(){
    await this.userManagementService.addRole(this.newRole);
    this.refreshRoles();
  }

  onDeletedUser(deleted:boolean){
    if(deleted)this.refreshUsers();
  }
  onDeletedRole(deleted:boolean){
    if(deleted)this.refreshRoles();
  }
  setRole(role:any){
    this.selectedRole=role;
  }
  submitUser(event: any){
    this.newUser.firstName=event.target.firstname.value;
    this.newUser.lastName=event.target.lastname.value;
    this.newUser.username =event.target.username.value;
    this.newUser.password =event.target.password.value;
    this.newUser.email = event.target.email.value;
    this.newUser.dateOfBirth=event.target.date.value;
    this.newUser.role =this.selectedRole;
    this.createNewUser();

  }

  submitRole(event: any){
    this.newRole.type=event.target.type.value;
    this.createNewRole();
  }
}