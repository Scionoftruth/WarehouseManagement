import { Component, OnInit} from '@angular/core';
import { Observable } from 'rxjs';
import { Item } from 'src/app/Item';
import { ItemService } from 'src/app/services/item.service';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent implements OnInit {
  orderHeaders= ["Item Name","Quantity","Price"]
  items: Observable<Item[]> = new Observable<Item[]>();
  constructor(private itemService:ItemService) { }

  ngOnInit(): void {
    this.items = this.itemService.subject;
  }

}
