import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {User} from '../_services/user-management/user'
import {UserManagementService} from '../_services/user-management/user-management.service'
import {Role} from "../_services/user-management/role";

@Component({
    selector: 'app-user-data',
    templateUrl: './user-data.component.html',
    styleUrls: ['./user-data.component.css']
})
export class UserDataComponent implements OnInit {
    @Input() user: User;
    @Output() onDeleted = new EventEmitter<boolean>();
    showEditDialog: Boolean = false;
    showDetailsDialog: Boolean = false;
    selectedRole: Role = new Role();
    RoleData: Role[];

    constructor(public userManagementService: UserManagementService) {
    }

    ngOnInit() {
        this.RoleData = [];
        this.refreshRoles();
    }

    async delete() {
        await this.userManagementService.deleteUser(this.user.id);
        this.onDeleted.emit(true);
    }

    async refreshRoles() {
        const data = await this.userManagementService.allRoles();
        if (data != undefined && data._embedded != undefined)
            this.RoleData = data._embedded.roleEntities;
        else this.RoleData = [];
        this.selectedRole = this.RoleData.find((role) => {
            return role.type === this.user.role.type
        });
    }

    setRole(role: any) {
        this.selectedRole = role;
    }

    submitUser(event: any) {
        this.user.firstName = event.target.firstname.value;
        this.user.lastName = event.target.lastname.value;
        this.user.username = event.target.username.value;
        this.user.email = event.target.email.value;
        this.user.dateOfBirth = event.target.date.value;
        this.user.role = this.selectedRole;
        this.updateUser();
    }

    async updateUser() {
        await this.userManagementService.editUser(this.user.id, this.user);
        this.showEditDialog = false;
    }
}
