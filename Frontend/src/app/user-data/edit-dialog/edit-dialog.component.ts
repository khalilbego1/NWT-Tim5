import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
selector: 'app-edit-dialog',
templateUrl: './edit-dialog.component.html',
styleUrls: ['./edit-dialog.component.css']
})
export class EditDialogComponent implements OnInit {

 @Input() closable = true;
    @Input() visible: boolean;
    @Output() visibleChange: EventEmitter<boolean> = new EventEmitter<boolean>();

    constructor() { }

    ngOnInit() { }

    close() {
        this.visible = false;
        this.visibleChange.emit(this.visible);
    }
}