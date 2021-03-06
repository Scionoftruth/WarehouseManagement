import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError, Subject } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Order } from '../Order';
import {Employee} from '../Employee';


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  
  orders: Order[] = [];
  subject: Subject<Order[]> = new Subject<Order[]>();
  constructor(private http: HttpClient) { }
  resData: String='';
   employee:Employee= {
     empId: 0,
     email: ''
   }

   login(email:string, password:string):Observable<Employee>{
     return this.http.post<Employee>("http://ec2-18-118-134-128.us-east-2.compute.amazonaws.com:8080/employee/login", JSON.stringify({email, password}),{
       headers:{
         "Content-Type":"application/json"
       }
     })
       .pipe(catchError((e)=>{
         return throwError(e);
       }));
   }

   acceptOrder(transactionId:Number, employeeId:Number):Observable<String>{
    return this.http.post<String>("http://ec2-18-118-134-128.us-east-2.compute.amazonaws.com:8080/employee/accept", JSON.stringify({transactionId, employeeId}),{
      headers:{
        "Content-Type":"application/json"
      }
    })
      .pipe(catchError((e)=>{
        return throwError(e);
      }));
  }

  completeOrder(transactionId:Number, employeeId:Number):Observable<String>{
    return this.http.post<String>("http://ec2-18-118-134-128.us-east-2.compute.amazonaws.com:8080/employee/complete", JSON.stringify({transactionId, employeeId}),{
      headers:{
        "Content-Type":"application/json"
      }
    })
      .pipe(catchError((e)=>{
        return throwError(e);
      }));
  }

  cancelOrder(transactionId:Number, employeeId:Number):Observable<String>{
    return this.http.post<String>("http://ec2-18-118-134-128.us-east-2.compute.amazonaws.com:8080/employee/cancel", JSON.stringify({transactionId, employeeId}),{
      headers:{
        "Content-Type":"application/json"
      }
    })
      .pipe(catchError((e)=>{
        return throwError(e);
      }));
  }

  
  getCustomerOrder(custId: Number) {
    this.http.get<Order[]>(`http://ec2-18-118-134-128.us-east-2.compute.amazonaws.com:8080/employee/customer/order/${custId}`)
    .pipe(
      catchError((e)=> {
        return throwError(e);
      }))
      .subscribe(
        (data) => {
          this.orders = data;
          this.subject.next(this.orders);
          console.log(data)
        }
      )
  }

  getByStatus(status: string) {
    this.http.get<Order[]>(`http://ec2-18-118-134-128.us-east-2.compute.amazonaws.com:8080/employee/bystatus/${status}`)
    .pipe(
      catchError((e)=> {
        return throwError(e);
      }))
      .subscribe(
        (data) => {
          this.orders = data;
          this.subject.next(this.orders);
        }
      )
  }

  addItem(itemId:number,itemName:string,itemPrice:number,itemQuantity:number){
    return this.http.post<String>("http://ec2-18-118-134-128.us-east-2.compute.amazonaws.com:8080/item/stock/add", JSON.stringify({itemId,itemName,itemPrice,itemQuantity}),{
      headers:{
        "Content-Type":"application/json"
      }
    })
      .pipe(catchError((e)=>{
        return throwError(e);
      }));
  }

}