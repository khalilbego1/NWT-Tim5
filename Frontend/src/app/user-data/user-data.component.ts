import { Component, OnInit, Input, Output,EventEmitter } from '@angular/core';
import {User} from '../_services/user-management/user'
import {UserManagementService}from '../_services/user-management/user-management.service'

@Component({
  selector: 'app-user-data',
  templateUrl: './user-data.component.html',
  styleUrls: ['./user-data.component.css']
})
export class UserDataComponent implements OnInit {
  @Input() user:User
  @Output() onDeleted = new EventEmitter<boolean>();
  constructor(public userManagementService:UserManagementService) { }

  ngOnInit() {
  }
  async delete(){
    await this.userManagementService.deleteUser(this.user.id)
    this.onDeleted.emit(true);

  }

}
