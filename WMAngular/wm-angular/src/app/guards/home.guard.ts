import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { CustomerService } from '../services/customer.service';
import { EmployeeService } from '../services/employee.service';

@Injectable({
  providedIn: 'root'
})
export class HomeGuard implements CanActivate {

  constructor(private customerService:CustomerService, private router:Router){}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if(this.customerService.customer.custId === 0){
      this.router.navigate(['/home']);
      return false;
    }
    else{
    return true;
    }
  }
  
}
