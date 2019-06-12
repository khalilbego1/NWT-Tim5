import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {AppComponent} from './app.component';
import {HelloComponent} from './hello.component';
import {NavbarComponent} from './navbar/navbar.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {AdminComponent} from './admin/admin.component';
import {UserDataComponent} from './user-data/user-data.component';
import {RoleDataComponent} from './role-data/role-data.component';
import {RouterModule} from '@angular/router';
import {APP_BASE_HREF} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {EmployeeComponent} from './employee/employee.component';
import {PassengerComponent} from './passenger/passenger.component';
import { EditDialogComponent } from './user-data/edit-dialog/edit-dialog.component';
import { DetailsDialogComponent } from './user-data/details-dialog/details-dialog.component';
import { EditRoleDialogComponent } from './role-data/edit-role-dialog/edit-role-dialog.component';
import { RoleDetailsDialogComponent } from './role-data/role-details-dialog/role-details-dialog.component';
import { LocationDataComponent } from './location-data/location-data.component';
import { TransportDataComponent } from './transport-data/transport-data.component'
import {ArrangementsComponent} from "./arrangements/arrangements.component";
import {FilterPipe} from "./FilterPipe/FilterPipe";
import {AdditionalActivityComponent} from "./arrangements/additional-activity/additional-activity.component";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        NgbModule,
        HttpClientModule,
        RouterModule.forRoot([
            {path: '', redirectTo: '/login', pathMatch: 'full'},
            {path: 'login', component: LoginComponent},
            {path: 'register', component: RegisterComponent},
            {path: 'admin', component: AdminComponent},
            {path: 'admin/:id', component: AdminComponent},
            {path: 'employee', component: EmployeeComponent},
            {path: 'arrangements', component: ArrangementsComponent},
            {path: 'additionalActivity', component: AdditionalActivityComponent},
        ])
    ],
    declarations: [AppComponent, HelloComponent, NavbarComponent, LoginComponent, RegisterComponent, AdminComponent, UserDataComponent, RoleDataComponent, EmployeeComponent, PassengerComponent, EditDialogComponent, DetailsDialogComponent, EditRoleDialogComponent, RoleDetailsDialogComponent, LocationDataComponent, TransportDataComponent, ArrangementsComponent, FilterPipe, AdditionalActivityComponent],
    bootstrap: [AppComponent],
    providers: [{provide: APP_BASE_HREF, useValue: '/'}],
})
export class AppModule{}
