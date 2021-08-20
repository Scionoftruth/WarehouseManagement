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


  constructor(private customerService:CustomerService, private router:Router) { }
//customer login
  login(): void{
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

//employee login
  // onSubmit2(): void{
  //   console.log(this.email, this.password);
  //   this.customerService.login(this.email,this.password)
  //     .subscribe(data=>{this.customerService.customer = {
  //       custId: data.custId,
  //       email:this.email
  //     }
  //     this.error=false;
  //     this.router.navigateByUrl('/employee-page');
  //   },
  //     (error)=>this.error=true);
  // }

  ngOnInit(): void {
  }

}
