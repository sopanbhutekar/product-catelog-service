package com.productcatalogservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productcatalogservice.model.Product;
import com.productcatalogservice.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public List<Product> getProductsByBrand(@RequestParam(value = "brand", required = false) String brand,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "color", required = false) String color,
			@RequestParam(value = "material", required = false) String material,
			@RequestParam(value="offset", required=false)Integer offset,
			@RequestParam(value="sizeOfPage", required=false)Integer sizeOfPage ) {
	
		return productService.getProductsByParameters(brand, size, color, material, offset, sizeOfPage);
	}
	
}
