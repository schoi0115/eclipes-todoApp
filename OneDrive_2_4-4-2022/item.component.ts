import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Item } from '../item';
import { TodoService } from '../todo.service';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent implements OnInit {

  constructor(private route:ActivatedRoute, private todoService:TodoService) { }
  id=0;
  item:Item|undefined;

  ngOnInit(): void {
    this.route.params.subscribe(
      parameters => {
        this.id = +parameters['id'];
        this.todoService.getItems().subscribe(
          itemArray => this.item = itemArray.find(todoitem=> todoitem.id==this.id),
          err => console.log(err)
        )
      },
      err => console.log(err)
    )
  }

}
