package com.productcatalogservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.productcatalogservice.model.Product;
import com.productcatalogservice.model.Seller;
import com.productcatalogservice.service.SellerService;

@RestController
public class SellerController {
	@Autowired
	private SellerService sellerService;
	
	@RequestMapping(value="/sellers", method=RequestMethod.POST)
	public int addSeller(@RequestBody Seller seller) {
		return sellerService.addSeller(seller);		
	}
	
	@RequestMapping(value="/sellers", method=RequestMethod.GET)
	public List<Seller> getSeller() {
		return sellerService.getSeller();		
	}
	
	@RequestMapping(value="/sellers/{sellerId}", method=RequestMethod.PUT)
	public void updateSeller(@PathVariable int sellerId, @RequestBody Seller seller) {
		sellerService.updateSeller(sellerId,seller);
	}

	@RequestMapping(value="/sellers/{sellerId}", method=RequestMethod.DELETE)
	public void deleteSeller(@PathVariable int sellerId) {
		sellerService.deleteSeller(sellerId);
	}
	
	@RequestMapping(value="/sellers/{sellerId}/products", method=RequestMethod.POST)
	public int addProductForSeller(@PathVariable int sellerId, @RequestBody Product product) {
		return sellerService.addProduct(sellerId, product);		
	}
	
	@RequestMapping(value="/sellers/{sellerId}/products/bulk", method=RequestMethod.POST)
	public void addProductForSeller(@PathVariable int sellerId, @RequestBody List<Product> products) {
		sellerService.addProducts(sellerId, products);		
	}
	
	@RequestMapping(value = "/sellers/{sellerId}/products", method = RequestMethod.GET)
	public List<Product> getProductsForSeller(@PathVariable("sellerId") int sellerId) {
		return sellerService.getProductsForSeller(sellerId);
	}

}
