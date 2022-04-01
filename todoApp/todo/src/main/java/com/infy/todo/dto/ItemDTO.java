package com.infy.todo.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ItemDTO {
	
	@Null
	private Integer id;
	
	@NotNull
	@Size(min = 3,message = "{Item.MIN_3_LETTERS}")
	@Pattern(regexp = "[A-Z][a-z]+", message = "Must begin with uppercase followed by lowercase letters")
	private String todoitem;
	
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ItemDTO() {
		super();
	}
	public ItemDTO(Integer id, String todoitem) {
		super();
		this.id = id;
		this.todoitem = todoitem;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTodoitem() {
		return todoitem;
	}
	public void setTodoitem(String todoitem) {
		this.todoitem = todoitem;
	}
	@Override
	public String toString() {
		return "ItemDTO [id=" + id + ", todoitem=" + todoitem + "]";
	}
	
	
}
