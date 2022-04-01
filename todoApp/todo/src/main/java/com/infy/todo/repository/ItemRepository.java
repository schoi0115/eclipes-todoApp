package com.infy.todo.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.todo.entity.Item;

public interface ItemRepository extends CrudRepository<Item, Integer> {

}
