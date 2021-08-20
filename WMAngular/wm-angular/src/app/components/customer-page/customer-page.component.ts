import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/Customer';

@Component({
  selector: 'app-customer-page',
  templateUrl: './customer-page.component.html',
  styleUrls: ['./customer-page.component.css']
})
export class CustomerPageComponent implements OnInit {

  firstName: String = ""
  lastName: String = ""
  password: String = ""
  email: String = ""
  streetNumber: String = ""
  address1: String = ""
  address2: String = ""
  zipcode: String = ""
  phoneNumber: String = ""
  item1: String = ""
  quantity1: String = ""
  item2: String = ""
  quantity2: String = ""
  item3: String = ""
  quantity3: String = ""

  constructor() { }

  placeOrder():void{
    alert("Order Submission Button Clicked")
    //logic to place order on click of submission button
  }





  ngOnInit(): void {
    
  }

}
