import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { AppComponent } from './app.component';
import { HelloComponent } from './hello.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AdminComponent } from './admin/admin.component';
import { UserDataComponent } from './user-data/user-data.component';
import { RoleDataComponent } from './role-data/role-data.component';
import { RouterModule } from '@angular/router';
import {APP_BASE_HREF} from '@angular/common';


@NgModule({
  imports:      [ 
                  BrowserModule, 
                  FormsModule ,
                  NgbModule,
                  RouterModule.forRoot([
                    {path:'', redirectTo:'/login',pathMatch:'full'},
                    {path:'login', component: LoginComponent},
                    {path:'register',component:RegisterComponent},
                    {path:'admin', component:AdminComponent}
                  ])
                ],
  declarations: [ AppComponent, HelloComponent, NavbarComponent, LoginComponent, RegisterComponent, AdminComponent, UserDataComponent, RoleDataComponent ],
  bootstrap:    [ AppComponent ],
  providers: [{provide: APP_BASE_HREF, useValue : '/' }],
})
export class AppModule { }
