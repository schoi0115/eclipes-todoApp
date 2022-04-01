package com.infy.todo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.todo.dto.ItemDTO;
import com.infy.todo.entity.Item;
import com.infy.todo.repository.ItemRepository;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepository;

	@Override
	public List<ItemDTO> getAllItems() {
		List<ItemDTO> itemDTOs = new ArrayList<ItemDTO>();
		itemRepository.findAll()
						.forEach(item -> itemDTOs.add(new ItemDTO(item.getId(), item.getTodoitem())));
		return itemDTOs;
	}

	@Override
	public ItemDTO getItem(Integer id) throws Exception {
		Item item = itemRepository.findById(id).orElseThrow(()-> new Exception("Id not in database"));
		return new ItemDTO(item.getId(), item.getTodoitem());
	}

	@Override
	public ItemDTO addItem(ItemDTO itemDTO) {
		Item item = itemRepository.save(new Item(null,itemDTO.getTodoitem()));
		return new ItemDTO(item.getId(),item.getTodoitem());
	}

	@Override
	public ItemDTO updateItem(ItemDTO newItemDTO) {
		Item item = itemRepository.findById(newItemDTO.getId()).orElseThrow();
		item.setTodoitem(newItemDTO.getTodoitem());
		return new ItemDTO(item.getId(),item.getTodoitem());
	}

	@Override
	public ItemDTO deleteItem(Integer id) {
		Item item = itemRepository.findById(id).orElseThrow();
		itemRepository.delete(item);  /// What do .remove() and .delete() methods do?
		                              /// they mark the entity for removal from persistence context at the end of the transaction 
									  /// IN JAVA WE CANNOT CONSCIOUSLY DELETE AN OBJECT -> Garbage Collection is the only way to delete objects 
		                              /// May be you can set all the values to null; YES, but .remove or .delete dont do that.
		return new ItemDTO(item.getId(),item.getTodoitem());
	}

	

}
