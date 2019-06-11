import {Destination} from '../locationTransport/destination'
import {AdditionalActivity} from "./additionalActivity";
import {TransportType} from "../locationTransport/transportType";
import {User} from "../user-management/user";

export class Arrangement {
    id?: number;
    name: string;
    destination: Destination;
    additionalActivity: AdditionalActivity;
    transportationType: TransportType;
    user: User;

    constructor(id:number, name:string, destination:Destination, additionalActivity:AdditionalActivity, transportationType:TransportType, user:User)
    {
        this.id = id;
        this.name = name;
        this.destination = destination;
        this.additionalActivity = additionalActivity;
        this.transportationType = transportationType;
        this.user = user;
    }
}