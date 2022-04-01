package com.infy.todo.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.todo.dto.ErrorDTO;
import com.infy.todo.dto.ItemDTO;
import com.infy.todo.service.ItemService;

@CrossOrigin
@Validated
@RestController /// since it is a presentation layer bean; it is like @Component but for presentation layer 
@RequestMapping("basePath")  // since there is no mention of "method", this base path works for GET, PUT, POST, DELETE all of them
public class ItemAPI {
	
	@Autowired
	private ItemService itemservice;
	
	@Autowired
	private Environment environment;
	
//	@RequestMapping(method = RequestMethod.GET,path = "hi")
//	public String sayHi() {
//		return "Hello World";
//	}
	
	@GetMapping("item")
	public ResponseEntity<ItemDTO> getItem(@RequestParam Integer id) throws Exception {
		return new ResponseEntity<ItemDTO>(itemservice.getItem(id) , HttpStatus.OK);
	}
	
	@GetMapping("items")
	public List<ItemDTO> allItems(){
		return itemservice.getAllItems();
	}
	
	@PostMapping("items")
	public ResponseEntity<ItemDTO> addItem(@RequestBody @Valid ItemDTO itemDTO) {
		// use the ITEM DTO to save it in database
		return new ResponseEntity<ItemDTO>(itemservice.addItem(itemDTO), HttpStatus.CREATED) ;
	}
	
	@DeleteMapping("items/{id}")
	public ItemDTO deleteItem(@PathVariable @Max(value = 100,message="{Item.ID_MAX_100}") Integer id) {
		return itemservice.deleteItem(id);
	}
	
	@PutMapping("items")
	public ItemDTO updateItem(@RequestBody ItemDTO itemDTO) {
		return itemservice.updateItem(itemDTO);
	}

}
