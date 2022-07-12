package com.citiustech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.entity.Product;
import com.citiustech.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryService;

	@PostMapping("/saveProduct")
	public ResponseEntity<Product> save(@RequestBody Product product) {
		Product saveProduct = inventoryService.save(product);
		return new ResponseEntity<Product>(saveProduct, HttpStatus.CREATED);
	}
	
	@GetMapping("/get-all-products")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = inventoryService.getAll();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
		Product product = inventoryService.getById(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		inventoryService.delete(id);
		return new ResponseEntity<String>("Product Deleted!...", HttpStatus.OK);
	}
	
	@PutMapping("/updateProduct")
	public ResponseEntity<Product> update(@RequestBody Product product) {
		Product newProduct = new Product(product);
		Product updateProduct = inventoryService.save(newProduct);
		return new ResponseEntity<Product>(updateProduct, HttpStatus.OK);
	}
	
	@GetMapping("/name")
	public ResponseEntity<Product> getProductByName(@RequestParam String name) {
		Product product = inventoryService.getByName(name);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
}
