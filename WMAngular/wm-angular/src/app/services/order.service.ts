import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { throwError,Observable } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Customer } from '../Customer';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  /*createOrder(custId:string, item:string, quantity:number)

  */
}
