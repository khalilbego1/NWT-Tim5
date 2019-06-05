import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {UserManagementService} from '../_services/user-management/user-management.service'
import {Role} from '../_services/user-management/role'

@Component({
    selector: 'app-role-data',
    templateUrl: './role-data.component.html',
    styleUrls: ['./role-data.component.css']
})
export class RoleDataComponent implements OnInit {

    constructor(public userManagementService: UserManagementService) {
    }

    @Input() role: Role;
    @Output() onDeleted = new EventEmitter<boolean>();

    ngOnInit() {
    }

    async delete() {
        await this.userManagementService.deleteRole(this.role.id)
        this.onDeleted.emit(true);

    }
}
