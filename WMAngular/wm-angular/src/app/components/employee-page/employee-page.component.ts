import { Component, OnInit,Input } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from '../../Employee';
import { EmployeeService } from '../../services/employee.service';
import { Order } from '../../Order';
@Component({
  selector: 'app-employee-page',
  templateUrl: './employee-page.component.html',
  styleUrls: ['./employee-page.component.css']
})
export class EmployeePageComponent implements OnInit {
  
  
  orders: Observable<Order[]> = new Observable<Order[]>();

  constructor(private employeeService:EmployeeService) { }

  ngOnInit(): void {
    this.employeeService.getCustomerOrder(1);
    this.orders = this.employeeService.subject;
    console.log(this.orders); 
  }

}
