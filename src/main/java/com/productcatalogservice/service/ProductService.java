package com.productcatalogservice.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.productcatalogservice.model.Product;
import com.productcatalogservice.model.Seller;
import com.productcatalogservice.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public int addProduct(Seller seller, Product product) {
		product.setSeller(seller);
		return productRepository.save(product).getProductId();
	}

	@Cacheable(value="productsForSeller", key="sellerId")
	public List<Product> getProductForSeller(int sellerId) {
		return productRepository.findBySellerSellerId(sellerId);
	}
	
	public List<Product> getProducts(Integer offset, Integer sizeOfPage) {
		List<Product> products=new ArrayList<Product>();
		productRepository.findAll().forEach(p->products.add(p));
		
		if(null!=offset && null!=sizeOfPage) {
			return products.stream().skip(offset).limit(sizeOfPage).collect(Collectors.toList());
		}else if(null!=offset) {
			return products.stream().skip(offset).collect(Collectors.toList());
		}else if(null!=sizeOfPage) {
			return products.stream().limit(sizeOfPage).collect(Collectors.toList());
		}
		return products;
	}
	
	@Cacheable(value="brand", key="#brand")
	public List<Product> getProductsByBrand(String brand){
		return productRepository.findByBrand(brand);
	}
	@Cacheable(value="color", key="#color")
	public List<Product> getProductsByColor(String color){
		System.out.println("inside getProductsByColor");
		return productRepository.findByColor(color);
	}
	@Cacheable(value="material", key="#material")
	public List<Product> getProductsByMaterial(String material){
		System.out.println("inside getProductsByMaterial");
		//return getProducts().stream().filter(p-> material.equalsIgnoreCase(p.getMaterial())).collect(Collectors.toList());
		return productRepository.findByMaterial(material);
	}
	@Cacheable(value="size", key="#size")
	public List<Product> getProductsBySize(int size){
		System.out.println("inside getProductsBySize");
		//return getProducts().stream().filter(p-> p.getSize()==size).collect(Collectors.toList());
		return productRepository.findBySize(size);
	}

	public void addProducts(Seller seller, List<Product> products) {
		products.stream().forEach(
				p -> { p.setSeller(seller);
					productRepository.save(p);
				}
				);		
	}

	public List<Product> getProductsByParameters(String brand, Integer size, String color, String material, Integer offset, Integer sizeOfPage) {
	
		List<Product> products=getProducts(offset, sizeOfPage);
		Set<Product> temp= new LinkedHashSet<Product>(products);
		
		if(null==brand && null== color && null==material && null==size) {
			return products;
		}
		
		if(null!=brand)
		{
			temp.retainAll(getProductsByBrand(brand));
		}
		
		if(null!=color) {
			temp.retainAll(getProductsByColor(color));
		}
		
		if(null!=size) {
			temp.retainAll(getProductsBySize(size));
		}
		
		if(null!=material) {
			temp.retainAll(getProductsByMaterial(material));
		}
		
		
		return new ArrayList<Product>(temp);
	}
	
}
