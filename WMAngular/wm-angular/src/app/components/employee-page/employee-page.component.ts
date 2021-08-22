import { Component, OnInit,Input } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from '../../Employee';
import { EmployeeService } from '../../services/employee.service';
import { Order } from '../../Order';
import { Item } from 'src/app/Item';
import { ItemService } from 'src/app/services/item.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-employee-page',
  templateUrl: './employee-page.component.html',
  styleUrls: ['./employee-page.component.css']
})
export class EmployeePageComponent implements OnInit {

  custId: String="";
  transactionId: string="";
  isShow = true;
  showItem = true;
  empId: string = this.employeeService.employee.empId.toString();
  res: Observable<String> = new Observable<String>();
  

  constructor(private employeeService:EmployeeService,private itemService:ItemService,private router:Router) { }

  // View order by customer id
  // view order by status
  // accept order
  // complete order
  // check stock - Already Done
  
  viewCustomerOrder(): void{
    this.isShow = !this.isShow;
    this.employeeService.getCustomerOrder(this.custId);
  }

  viewStock(): void{
    this.showItem = false;
    this.itemService.getAllItems();
  }

  acceptOrder(): void{
    this.res =this.employeeService.acceptOrder(this.transactionId, this.empId);
    console.log(this.res);
  }
  ngOnInit(): void {
    //We might want to look into local storage because if we refresh the page, we lose Observable
    if (this.employeeService.employee.empId == 0) {
      this.router.navigateByUrl('/home');
    }
    console.log(this.employeeService.employee.empId)
  }

}
