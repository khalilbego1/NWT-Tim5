import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User }from './user'
import { LoginInput } from './loginInput'
import { environment } from '../environment'
import { Role } from './role';

const baseUrl = environment.url+'/travelAgency/user-service'

@Injectable({
  providedIn: 'root'
})
export class UserManagementService {

  constructor(private http: HttpClient) { }

  private async request(method: string, url: string, data?: any){
    const result = this.http.request(method, url, {
      body: data,
      responseType: 'json',
      observe: 'body'
    });
    return new Promise<any>((resolve, reject) => {
      result.subscribe(resolve as any, reject as any);
    });
  }

  login (loginInput:LoginInput){
    return this.request('post',baseUrl+'/login', loginInput)
  }
  AllUsers(){
    return this.request('get',baseUrl+'/users')
  }
  oneUser(id:number){
    return this.request('get',baseUrl+'/users/'+String(id))
  }
  registration(user: User){
    return this.request('post',baseUrl+'/registration',user)
  }
  editUser(id:number,user: User){
    return this.request('put',baseUrl+'/users/'+String(id),user);
  }
  deleteUser(id:number){
    return this.request('delete',baseUrl+'/users/'+String(id))
  }
  allRoles(){
    return this.request('get',baseUrl+'/roles');
  }
  oneRole(id:number){
    return this.request('get',baseUrl+'/roles'+String(id))
  }
  usersForRole(id:number){
    return this.request('get',baseUrl+'/roles/'+String(id)+'/users')
  }
  addRole(role:Role){
    return this.request('post',baseUrl+'/roles',role)
  }
  editRole(id:number,role:Role){
    return this.request('put',baseUrl+'/roles/'+String(id),role)
  }
  deleteRole(id:number){
    return this.request('delete',baseUrl+'/roles'+String(id))
  }

}
