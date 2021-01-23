package com.productcatalogservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.productcatalogservice.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
	public List<Product> findBySellerSellerId(int sellerId);
	public List<Product> findByBrand(String brand);
	public List<Product> findByColor(String color);
	public List<Product> findByMaterial(String material);
	public List<Product> findBySize(int size);
}
