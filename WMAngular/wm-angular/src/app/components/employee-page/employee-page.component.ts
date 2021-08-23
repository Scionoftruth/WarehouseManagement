import { Component, OnInit,Input } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from '../../Employee';
import { EmployeeService } from '../../services/employee.service';
import { OrderService } from '../../services/order.service';
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
  //num: number = Number(localStorage.getItem('empId'))
  custId: Number=0;
  transId: Number=0;
  isShow = true;
  showItem = true;
  showForModify = true;
  showByStatus = true;
  empId: number = Number(localStorage.getItem('empId'))
  itemName: string=''
  itemPrice: number=0
  itemQuantity: number=0
  itemId:number=0
  loggedIn=''
  
  res: Observable<String> = new Observable<String>();
  status: string="";
  
  

  constructor(private employeeService:EmployeeService, private itemService:ItemService, private router:Router, private orderService:OrderService) { }

  // View order by customer id
  // view order by status
  // accept order
  // complete order
  // check stock - Already Done
  
  viewCustomerOrder(): void{
    this.isShow = false;
    this.showItem = true;
    this.showForModify = true;
    this.showByStatus = true;
    localStorage.setItem('action','custId');
    this.employeeService.getCustomerOrder(this.custId);
  }

  viewStock(): void{
    this.showItem = false;
    this.isShow = true;
    this.showForModify = true;
    this.showByStatus = true;
    this.itemService.getAllItems();
  }
  viewAllOrders(): void{
    localStorage.setItem('action','allOrders');
    this.orderService.getAllOrders();
    this.showForModify = false;
    this.showItem = true;
    this.isShow = true;
    this.showByStatus = true;
  }

  acceptOrder(): void{
    this.employeeService.acceptOrder(this.transId, this.empId)
    .subscribe(data=>{this.employeeService.resData=data})
    this.viewAllOrders();
  }
  completeOrder(): void{ 
    this.employeeService.completeOrder(this.transId, this.empId)
    .subscribe(data=>{this.employeeService.resData=data});
    this.viewAllOrders();
  }

  cancelOrder(): void{
    this.employeeService.cancelOrder(this.transId, this.empId)
    .subscribe(data=>{this.employeeService.resData=data});
    this.viewAllOrders();
  }

  viewByStatus(): void{
      localStorage.setItem('action','byStatus');
      //console.log(this.status);
      this.employeeService.getByStatus(this.status);
      this.showByStatus = false;
      this.showForModify = true;
      this.showItem = true;
      this.isShow = true;
  }

  addItem(): void{
    this.employeeService.addItem(this.itemId, this.itemName, this.itemPrice, this.itemQuantity)
    .subscribe(data=>{this.employeeService.resData=data});
  }

  

  ngOnInit(): void {
    if (Number(localStorage.getItem('empId')) == 0) {
      this.router.navigateByUrl('/home');
    }
    //this.loggedIn = localStorage.getItem('email');
    //let email=localStorage.getItem('email');
    //document.getElementById("loggedin")?.innerText=`${email}`
  }

}
