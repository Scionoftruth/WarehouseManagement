import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/Customer';
import { Observable } from 'rxjs';
import { CustomerService } from '../../services/customer.service';
import { Router } from '@angular/router';
import { InvokeFunctionExpr } from '@angular/compiler';
import { OrderService } from 'src/app/services/order.service';
import { Item } from 'src/app/Item';
import { ItemService } from 'src/app/services/item.service';

@Component({
  selector: 'app-customer-page',
  templateUrl: './customer-page.component.html',
  styleUrls: ['./customer-page.component.css']
})

export class CustomerPageComponent implements OnInit {
  firstName: string = ""
  lastName: string = ""
  password: string = ""
  email: string = ""
  //streetNumber: string = ""
  address: string = ""
  //address2: string = ""
  city: string=""
  zipCode: string = ""
  //phoneNumber: string = ""
  itemId: number = 0
  invQuantity: number = 0
  //item2: string = ""
  //quantity2: string = ""
  //item3: string = ""
  //quantity3: string = ""
  custId:number=Number(localStorage.getItem('custId'))
  items: Observable<Item[]> = new Observable<Item[]>();
  itemQuantity: number=0

  placeOrder():void{
    //console.log(this.custId)
    alert("Clicked")
    this.orderService.createOrder(this.custId,this.itemId,this.itemQuantity)
    .subscribe(data=>{this.orderService.resData=data});
    console.log(this.custId)
    console.log(this.itemQuantity)
    console.log(this.itemId)
    //console.log(this.orderService)
  }

  invoice():void{
    this.customerService.invoice(this.custId);
  }


  //register?
  //


  ngOnInit(): void {
    if (Number(localStorage.getItem('custId')) == 0) {
      this.router.navigateByUrl('/home');
    }
    this.itemService.getAllItems();
    this.items = this.itemService.subject;
    console.log(this.items);
  }

  constructor(private customerService:CustomerService, private router:Router, private orderService:OrderService, private itemService:ItemService) {}

   


}