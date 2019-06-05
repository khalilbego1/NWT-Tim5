import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Transport} from './transport'
import {TransportType} from './transportType'

const baseUrl = 'http://localhost:8090/transportations';

@Injectable({
    providedIn: 'root'
})
export class TransportService {

    constructor(private http: HttpClient) {
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

    allTransport() {
        return this.request('get', baseUrl + '/')
    }

    oneTransport(id: number) {
        return this.request('get', baseUrl + '/' + String(id))
    }

    allTransportOfType(type: TransportType) {
        return this.request('get', baseUrl + '/types', type)
    }

    addTransport(trans: Transport) {
        return this.request('post', baseUrl + '/', trans)
    }

    editTransport(id: number, trans: Transport) {
        return this.request('put', baseUrl + '/' + String(id), trans);
    }

    deleteTransport(id: number) {
        return this.request('delete', baseUrl + '/' + String(id))
    }

    allTransportTypes() {
        return this.request('get', baseUrl + '/types')
    }

    oneTransportType(id: number) {
        return this.request('get', baseUrl + '/types' + String(id))
    }

    addTransportType(transT: TransportType) {
        return this.request('post', baseUrl + '/types', transT)
    }

    editTransportType(id: number, transT: TransportType) {
        return this.request('put', baseUrl + '/types' + String(id), transT);
    }

    deleteTransportType(id: number) {
        return this.request('delete', baseUrl + '/types' + String(id))
    }
}
