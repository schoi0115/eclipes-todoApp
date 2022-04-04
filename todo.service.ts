import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Item } from './item';

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  constructor(private http:HttpClient) { }
  baseUrl = "http://localhost:3333/basePath/";

  updateItem(updatedItem:Item){
    return this.http.put<Item>(this.baseUrl+"items",updatedItem);
  }
  
  deleteItem(id:number){
    return this.http.delete<Item>(this.baseUrl+"items/"+id);
  }

  addItem(newItem:Item){
    return this.http.post<Item>(this.baseUrl+"items",newItem);
  }
  
  getItems(){
    return this.http.get<Item[]>(this.baseUrl+"items");
  }


}
