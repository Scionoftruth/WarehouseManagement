import { Component, OnInit, Input } from '@angular/core';
import { Order } from '../../Order';
@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  @Input() order: Order = {
    orderQty: 0,
    itemName: '',
    itemPrice: 0,
    invQuantity: 0,
    status: '',
    customerName: '',
    employeeName: '',
    customerAddress: ''
  }
  constructor() { }

  ngOnInit(): void {
  }

}
