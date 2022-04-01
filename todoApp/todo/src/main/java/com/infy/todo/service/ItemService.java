package com.infy.todo.service;

import java.util.List;

import com.infy.todo.dto.ItemDTO;

public interface ItemService {
	
	List<ItemDTO> getAllItems();
	ItemDTO getItem(Integer id) throws Exception;
	ItemDTO addItem(ItemDTO itemDTO);
	ItemDTO updateItem(ItemDTO newItemDTO);
	ItemDTO deleteItem(Integer id);

}
