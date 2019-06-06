import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
    selector: 'app-role-details-dialog',
    templateUrl: './role-details-dialog.component.html',
    styleUrls: ['./role-details-dialog.component.css']
})
export class RoleDetailsDialogComponent implements OnInit {

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