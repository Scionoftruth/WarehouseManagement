import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  email: string='';
  password: string='';
  error: boolean=false;
  firstName: string='';
  lastName: string='';
  address: string='';
  city: string='';
  state: string='';
  zipCode: number=0;


  constructor(private customerService:CustomerService, private router:Router) { }

  onSubmit(): void{
    console.log(this.email, this.password);
    this.customerService.login(this.email,this.password)
      .subscribe(data=>{this.customerService.customer = {
        custId: data.custId,
        email:this.email
      }
      this.error=false;
      this.router.navigateByUrl('/customer-page');
    },
      (error)=>this.error=true);
  }

  onSubmit2(): void{
    console.log(this.email, this.password);
    this.customerService.login(this.email,this.password)
      .subscribe(data=>{this.customerService.customer = {
        custId: data.custId,
        email:this.email
      }
      this.error=false;
      this.router.navigateByUrl('/employee-page');
    },
      (error)=>this.error=true);
  }

  onSubmit3(): void{
    console.log(this.firstName,this.lastName,this.address,this.city,this.state,this.zipCode,this.email, this.password);
    this.customerService.register(this.firstName,this.lastName,this.address,this.city,this.state,this.zipCode,this.email,this.password)
      .subscribe(data=>{this.customerService.customer = {
        custId: data.custId,
        email:this.email
      }
      this.error=false;
      this.router.navigateByUrl('/home-page');
    },
      (error)=>this.error=true);
  }

  ngOnInit(): void {
  }

}
