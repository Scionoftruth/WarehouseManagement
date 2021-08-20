import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HomePageComponent } from './components/home-page/home-page.component';
import { EmployeePageComponent } from './components/employee-page/employee-page.component';
import { CustomerPageComponent } from './components/customer-page/customer-page.component';
import { ProfileComponent } from './components/profile/profile.component';
import { HomeGuard } from './guards/home.guard';

const routes: Routes = [
  {path:'home', component:HomePageComponent},
{path:'employee-page', component:EmployeePageComponent/*, canActivate: [HomeGuard]*/},
{path:'customer-page', component:CustomerPageComponent/*, canActivate: [HomeGuard]*/},
  {path:'profile', component:ProfileComponent},
  {path:'', redirectTo:'/home',pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
