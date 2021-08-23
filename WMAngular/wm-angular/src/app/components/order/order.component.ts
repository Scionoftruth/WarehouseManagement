import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from '../../Order';
import { EmployeeService } from '../../services/employee.service';
import { OrderService } from '../../services/order.service';
@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  
  orderHeaders= ["Transaction ID","Item Name","Order Quantity","Price","Quantity Inventory","Status","Customer Name","Employee Name","Address"]
  orders: Observable<Order[]> = new Observable<Order[]>();
  orders1: Observable<Order[]> = new Observable<Order[]>();
  
  constructor(private employeeService:EmployeeService, private orderService:OrderService) { }

  ngOnInit(): void {
    if(localStorage.getItem('action') == 'custId'){
      //console.log(localStorage.getItem('action'))
      this.orders = this.employeeService.subject;
    } else if (localStorage.getItem('action') == 'allOrders') {
      //console.log(localStorage.getItem('action'))
      this.orders = this.orderService.subject;
    } else if (localStorage.getItem('action') == 'byStatus'){
      this.orders = this.employeeService.subject;
    }
    
  }

}
