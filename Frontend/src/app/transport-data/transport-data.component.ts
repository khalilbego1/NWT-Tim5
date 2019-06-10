import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Transport} from "../_services/locationTransport/transport";
import {TransportService} from "../_services/locationTransport/transport.service";

@Component({
    selector: 'app-transport-data',
    templateUrl: './transport-data.component.html',
    styleUrls: ['./transport-data.component.css']
})
export class TransportDataComponent implements OnInit {

    @Input() transportation: Transport;
    @Output() onDeleted = new EventEmitter<boolean>();

    constructor(public transportService: TransportService) {
    }

    ngOnInit() {
    }

    async delete() {
        await this.transportService.deleteTransport(this.transportation.id);
        this.onDeleted.emit(true);
    }
}
