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


@NgModule({
  imports:      [ BrowserModule, FormsModule ,NgbModule],
  declarations: [ AppComponent, HelloComponent, NavbarComponent, LoginComponent, RegisterComponent, AdminComponent ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
