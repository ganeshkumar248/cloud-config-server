package com.citiustech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.model.Product;
import com.citiustech.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/saveProduct")
	public Product save(@RequestBody Product product) {
		Product saveProduct = productService.save(product);
		return saveProduct;
	}
	
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable("id") Long id) {
		Product product = productService.getById(id);
		return product;
	}
	
	@DeleteMapping("/{id}") 
	public String delete(@PathVariable("id") Long id) {
		productService.delete(id);
		return "Product Deleted!...";
	}
	
	@PutMapping("/updateProduct")
	public Product update(@RequestBody Product product) {
		Product newProduct = new Product(product);
		Product updateProduct = productService.save(newProduct);
		return updateProduct;
	}
	
	@GetMapping("/name")
	public Product getProductByName(@RequestParam String name) {
		Product product = productService.getByName(name);
		return product;
	}
	
}





















