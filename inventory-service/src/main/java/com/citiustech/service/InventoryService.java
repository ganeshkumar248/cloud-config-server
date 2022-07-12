package com.citiustech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citiustech.entity.Product;
import com.citiustech.repository.InventoryRepository;

@Service
public class InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;
	
	public Product save(Product product) {
		return inventoryRepository.save(product);
	}
	
	public List<Product> getAll() {
		return inventoryRepository.findAll();
	}
	
	public Product getById(Long id) {
		return inventoryRepository.findById(id).get();
	}
	
	public void delete(Long id) {
		Product product = getById(id);
		inventoryRepository.delete(product);
	}
	
	public Product getByName(String name) {
		return inventoryRepository.findByName(name);
	}
	
}
