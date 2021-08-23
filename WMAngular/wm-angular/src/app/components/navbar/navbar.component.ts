import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  logout():void{
    localStorage.clear();
    this.router.navigateByUrl('/home')
  }

  constructor(private router:Router) { }

  ngOnInit(): void {
  }

}
