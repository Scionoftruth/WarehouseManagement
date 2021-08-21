import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/Customer';
import { Observable } from 'rxjs';
import { CustomerService } from '../../services/customer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer-page',
  templateUrl: './customer-page.component.html',
  styleUrls: ['./customer-page.component.css']
})

export class CustomerPageComponent implements OnInit {
  password: string = ""
  email: string = ""
  custId: number = 0;

  createOrder():void{
    alert("Order Submission Button Clicked")
    console.log(this.custId)
    //pass the user thats logged in ?
    this.customerService.login(this.email, this.password)
      .subscribe(data=>{this.customerService.customer = {
        email: data.email,
        custId: data.custId,
      }
      this.error=false;
      this.router.navigateByUrl('/customer-page');
    },
      (error)=>this.error=true); 

  }
  ngOnInit(): void {
    this.customerService.invoice(this.custId);

  }

  constructor(private customerService:CustomerService, private router:Router) {}

   


}