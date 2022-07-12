package com.citiustech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.citiustech.model.Product;
import com.citiustech.repository.ProductRepository;

@Service
@RefreshScope
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	@Lazy
	private RestTemplate restTemplate;
	
	@Value("${microservice.inventory-service.endpoints.endpoint.uri}")
	private String ENDPOINT_URL;
	
	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	public List<Product> getAll() {
		return productRepository.findAll();
	}
	
	public Product getById(Long id) {
		Product product = productRepository.findById(id).get();
		if(product == null) {
			product = restTemplate.getForObject("ENDPOINT_URL"+"/"+id, Product.class);
		}
		return product;
	}
	
	public void delete(Long id) {
		Product product = getById(id);
		productRepository.delete(product);
	}
	
	public Product getByName(String name) {
		Product product = productRepository.findByName(name);
		if(product == null) {
			product = restTemplate.getForObject("ENDPOINT_URL"+"/"+name, Product.class);
		}
		return product;
	}
	
}
























