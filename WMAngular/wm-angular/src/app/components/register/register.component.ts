import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  firstName: String = ""
  lastName: String = ""
  password: String = ""
  email: String = ""
  address: String = ""
  city: String = ""
  state: String = ""
  zipcode: String = ""

  createReg():void{
    alert("New Registration Submission Clicked")

    console.log(this.firstName,this.lastName,this.address,this.city,this.state,this.zipcode,this.email, this.password);
    this.customerService.register(this.firstName,this.lastName,this.address,this.city,this.state,this.zipcode,this.email,this.password)
      .subscribe(data=>{this.customerService.customer = {
        custId: data.custId,
        email:this.email
      }
      this.error=false;
      this.router.navigateByUrl('/home-page');
    },
      (error)=>this.error=true);
  
  }

  constructor() {}

  ngOnInit(): void {
  }

}