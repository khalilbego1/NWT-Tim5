import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Transport} from "../_services/locationTransport/transport";

@Component({
    selector: 'app-transport-data',
    templateUrl: './transport-data.component.html',
    styleUrls: ['./transport-data.component.css']
})
export class TransportDataComponent implements OnInit {

    @Input() transportation: Transport;
    @Output() onDeleted = new EventEmitter<Transport>();

    constructor() {
    }

    ngOnInit() {
    }

}
