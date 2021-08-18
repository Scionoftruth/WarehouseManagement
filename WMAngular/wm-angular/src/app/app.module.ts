import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { CustomerPageComponent } from './components/customer-page/customer-page.component';
import { ProfileComponent } from './components/profile/profile.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { EmployeePageComponent } from './components/employee-page/employee-page.component';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    CustomerPageComponent,
    ProfileComponent,
    NavbarComponent,
    EmployeePageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
