import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/Customer';
import { CustomerService } from 'src/app/services/customer.service';
import { OrderService } from 'src/app/services/order.service';

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
  itemQuantity: number = 0
  //item2: string = ""
  //quantity2: string = ""
  //item3: string = ""
  //quantity3: string = ""
  custId:number=this.customerService.customer.custId

  constructor(private customerService:CustomerService,private orderService:OrderService) { }

  placeOrder():void{
    this.orderService.createOrder(this.custId,this.itemId,this.itemQuantity);
  }

  invoice():void{
    this.customerService.invoice(this.custId);
  }

  




  ngOnInit(): void {
    
  }

}
