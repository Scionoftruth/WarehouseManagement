import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from '../../Order';
import { EmployeeService } from '../../services/employee.service';
@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  
  orderHeaders= ["Item Name","Order Quantity","Price","Quantity Inventory","Status","Customer Name","Employee Name","Address"]
  orders: Observable<Order[]> = new Observable<Order[]>();
  constructor(private employeeService:EmployeeService) { }

  ngOnInit(): void {
    this.orders = this.employeeService.subject;
  }

}
