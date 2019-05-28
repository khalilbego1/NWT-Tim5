import {Role} from './role'

export class User{
    id?:number
    firstName:string;
    lastName:string;
    username:string;
    password:string;
    email:string;
    dateOfBirth:string;
    role:Role;
}