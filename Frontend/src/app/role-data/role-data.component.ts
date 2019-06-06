import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {UserManagementService} from '../_services/user-management/user-management.service'
import {Role} from '../_services/user-management/role'

@Component({
    selector: 'app-role-data',
    templateUrl: './role-data.component.html',
    styleUrls: ['./role-data.component.css']
})
export class RoleDataComponent implements OnInit {
    @Input() role: Role;
    @Output() onDeleted = new EventEmitter<boolean>();
    showEditDialog: Boolean = false;
    showDetailsDialog: Boolean = false;

    constructor(public userManagementService: UserManagementService) {
    }

    ngOnInit() {
    }

    async delete() {
        await this.userManagementService.deleteRole(this.role.id);
        this.onDeleted.emit(true);
    }

    submitRole(event: any) {
        this.role.type = event.target.type.value;
        this.role.description = event.target.description.value;
        this.updateRole();
    }

    async updateRole() {
        await this.userManagementService.editRole(this.role.id, this.role);
        this.showEditDialog = false;
    }
}
