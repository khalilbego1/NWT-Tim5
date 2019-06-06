import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
    selector: 'app-edit-role-dialog',
    templateUrl: './edit-role-dialog.component.html',
    styleUrls: ['./edit-role-dialog.component.css']
})
export class EditRoleDialogComponent implements OnInit {

    @Input() closable = true;
    @Input() visible: boolean;
    @Output() visibleChange: EventEmitter<boolean> = new EventEmitter<boolean>();

    constructor() {
    }

    ngOnInit() {
    }

    close() {
        this.visible = false;
        this.visibleChange.emit(this.visible);
    }
}