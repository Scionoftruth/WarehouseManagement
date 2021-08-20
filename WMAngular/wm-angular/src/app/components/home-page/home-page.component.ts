import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from 'src/app/services/customer.service';
import { EmployeeService } from 'src/app/services/employee.service';


@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})

export class HomePageComponent implements OnInit {
  employeediv:boolean=false;
  customerdiv:boolean=false;
  registerdiv:boolean=false;
  email: string='';
  password: string='';
  error: boolean=false;

  constructor(private customerService:CustomerService,private employeeService:EmployeeService, private router:Router) { }
  
  
  empFunction(){
    this.employeediv=true;
    this.customerdiv=false;
    this.registerdiv=false
  }
  
  cusFunction(){
    this.employeediv=false;
    this.customerdiv=true;
    this.registerdiv=false
  }
  regFunction(){
    this.employeediv=false;
    this.customerdiv=false;
    this.registerdiv=true
  }

//customer login
  login(): void{
    //console.log(this.email, this.password);
    this.customerService.login(this.email,this.password)
      .subscribe(data=>{this.customerService.customer = {
        custId: data.custId,
        email: this.email
      }
      this.error=false;
      this.router.navigateByUrl('/customer-page');
      //console.log(data);
      //console.log(data.custId);
    },
      (error)=>this.error=true); 
  }

//employee login
  employeeLogin(): void{
    console.log(this.email, this.password);
    this.employeeService.login(this.email,this.password)
      .subscribe(data=>{this.employeeService.employee = {
        empId: data.empId,
        email:this.email
      }
      this.error=false;
      this.router.navigateByUrl('/employee-page');
    },
      (error)=>this.error=true);
  }

  ngOnInit(): void {
  }

}

