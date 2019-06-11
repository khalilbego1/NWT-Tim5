import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Arrangement} from "./arrangement";

const baseUrl = 'http://localhost:8585/travelAgency/arrangement-service';

@Injectable({
    providedIn: 'root'
})
export class ArrangementsService {

    arrangements:Arrangement[] = [];

    constructor(private http: HttpClient) {
        this.initiateArrangements();
    }

    private async initiateArrangements()
    {
        const data = await this.allArrangements();
        if (data != undefined && data._embedded != undefined)
            this.arrangements = data._embedded.userEntities;
        else this.arrangements = [];
    }

    private async request(method: string, url: string, data?: any) {
        let headers: HttpHeaders = new HttpHeaders();
        const accessToken = localStorage.getItem("token");
        if (accessToken) {
            headers = headers.append('Authorization', 'Bearer ' + accessToken);
            headers = headers.append('Access-Control-Allow-Origin', 'http://localhost:4200');
        }

        const result = this.http.request(method, url, {
            headers: headers,
            body: data,
            responseType: 'json',
            observe: 'body'

        });
        return new Promise<any>((resolve, reject) => {
            result.subscribe(resolve as any, reject as any);
        });
    }

    allArrangements() {
        return this.request('get', baseUrl + '/arrangements');
    }

    loadArrangementById(id: number)
    {
        return this.arrangements.find(x => x.id === id);
    }
}
