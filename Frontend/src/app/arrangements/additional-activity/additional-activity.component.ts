import {Component, Input, OnInit} from "@angular/core";
import {AdditionalActivity} from "../../_services/arrangements/additionalActivity";
import {ArrangementsService} from "../../_services/arrangements/arrangements.service";

@Component({
    selector: 'app-additional-activity',
    templateUrl: './additional-activity.component.html',
    styleUrls: ['./additional-activity.component.css']
})

export class AdditionalActivityComponent implements OnInit {
    @Input()
    additionalActivity: AdditionalActivity;

    constructor() {

    }

    submitAdditionalActivity(event: any) {
        this.additionalActivity.name = event.target.name.value;
        this.additionalActivity.description = event.target.description.value;
        this.updateAdditionalActivity();
    }

    updateAdditionalActivity()
    {

    }

    ngOnInit() {
        this.additionalActivity = new AdditionalActivity();
    }
}