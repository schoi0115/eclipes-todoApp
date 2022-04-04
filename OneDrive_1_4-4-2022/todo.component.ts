import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Item } from '../item';
import { TodoService } from '../todo.service';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {

  items:Item[]=[];
  errorMessage="";
  addItemForm: FormGroup = new FormGroup({});
  updateItemForm:FormGroup = new FormGroup({});
  //itemToUpdate:Item | undefined={id:0,todoitem:"",message:""};
  idToUpdate: number = 0;

  constructor(private todoService:TodoService,private fb:FormBuilder) { }

  displayUpdateForm(item:Item){
    
    // updateItemForm should have values corresp to the item with item id as given 
    this.idToUpdate = item.id;
    this.updateItemForm.setValue(item);
    // below 2 lines do the same as the above 1 line
    // this.updateItemForm.get('id')?.setValue(item.id);
    // this.updateItemForm.get('todoitem')?.setValue(item.todoitem);
  }

  updateItem(){   
    this.todoService.updateItem(this.updateItemForm.value).subscribe(
      updatedItem=>{ // update my local copy of this item in the items array; Binding will cause re-rendering
        this.items = this.items.map(item=> item.id==updatedItem.id ? updatedItem : item );
      },
      err => {
        this.errorMessage="Could not update item";
      }
    );
    // hide my update form
    this.idToUpdate=0;
  }

  deleteItem(id:number){
    this.todoService.deleteItem(id).subscribe(
      deletedItem => {  // need to remove it from items array and BINDING will cause view to remove it as well
        this.items = this.items.filter(item=>item.id!=deletedItem.id);
      },
      err => {
        this.errorMessage="Could not delete item";
      }
    )
  }

  addItem(){
    this.todoService.addItem(this.addItemForm.value).subscribe(
      createdItem => {
        // add the created Item to the current array of items we have WHICH WILL ADD IT TO THE DISPLAY
        this.items.push(createdItem);
      },
      err => {
        this.errorMessage="Could not add item. Please try later.";
      }
    )
  }

  ngOnInit(): void {

    this.addItemForm = this.fb.group({
      "id":[null,[]],
      "todoitem":['',[Validators.required,Validators.pattern("^[A-Z][a-z]+$"),Validators.minLength(3)]]
    });

    this.updateItemForm = this.fb.group({
      "id":[null,[]],
      "todoitem":['',[Validators.required,Validators.pattern("^[A-Z][a-z]+$"),Validators.minLength(3)]]
    });

    this.todoService.getItems().subscribe(
      itemArr => {
        this.items=itemArr;
      },
      err => {
        this.errorMessage="Todo Items could not be fetched";
      }
    );
  }

}
