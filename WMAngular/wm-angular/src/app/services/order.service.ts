import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { throwError,Observable, Subject } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { CustomerService } from './customer.service';
import { Order } from '../Order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  custId:number=this.customerService.customer.custId
  itemId:number=0
  quantity:number=0
  orders: Order[]=[];
  subject: Subject<Order[]> = new Subject<Order[]>();
  orderId:number=0;
  resData: String ="";


  constructor(private http: HttpClient, private customerService:CustomerService) { }

  createOrder(custId:number, item:number, quantity:number):Observable<String>{
      return this.http.post<String>("http://localhost:8080/customer/neworder", JSON.stringify({custId,item,quantity}),{
        headers:{
          "Content-Type":"application/json"
        }
      })
        .pipe(catchError((e)=>{
          return throwError(e);
        }));
  }

  getAllOrders(){
    this.http.get<Order[]>('http://localhost:8080/order/getorders')
    .pipe(
      catchError((e)=>{
        return throwError(e);
      }))
      .subscribe(
        (data) => {
          this.orders = data;
          this.subject.next(this.orders);
          //console.log(data)
        }
      )
  }

  getOrderById(orderId:number):Observable<Order>{
    return this.http.get<Order>(`http://localhost:8080/order/getorders/${orderId}`)
    .pipe(
      catchError((e) => {
        return throwError(e);
      }));

      
  }
  
  
}
