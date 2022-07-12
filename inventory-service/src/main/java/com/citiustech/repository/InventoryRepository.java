package com.citiustech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citiustech.entity.Product;

@Repository
public interface InventoryRepository extends JpaRepository<Product, Long> {

	public Product findByName(String name);

}
