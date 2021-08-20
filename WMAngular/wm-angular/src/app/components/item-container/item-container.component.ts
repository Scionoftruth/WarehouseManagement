import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Item } from 'src/app/Item';
import { ItemService } from 'src/app/services/item.service';

@Component({
  selector: 'app-item-container',
  templateUrl: './item-container.component.html',
  styleUrls: ['./item-container.component.css']
})
export class ItemContainerComponent implements OnInit {

  items: Observable<Item[]> = new Observable<Item[]>();

  constructor(private itemService:ItemService) { }

  ngOnInit(): void {
    this.itemService.getAllItems();
    this.items = this.itemService.subject;
  }

}
