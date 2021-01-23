package com.productcatalogservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productcatalogservice.model.Product;
import com.productcatalogservice.model.Seller;
import com.productcatalogservice.repository.SellerRepository;

@Service
public class SellerService {
	@Autowired
	private SellerRepository sellerRepository;
	
	@Autowired
	private ProductService productService;

	public int addSeller(Seller seller) {
		return sellerRepository.save(seller).getSellerId();
	}

	public List<Seller> getSeller() {
		List<Seller> seller=new ArrayList<Seller>();
		sellerRepository.findAll().forEach(p->seller.add(p));
		return seller;
	}	
	
	public void updateSeller(int sellerId, Seller seller) {
		seller.setSellerId(sellerId);
		sellerRepository.save(seller);
	}
	
	public void deleteSeller(int sellerId) {
		sellerRepository.deleteById(sellerId);
	}
	
	public int addProduct(int sellerId, Product product) {
		int productId=0;
		Optional<Seller> seller=sellerRepository.findById(sellerId);
		if(seller.isPresent()) {
		 productId=productService.addProduct(seller.get(), product);
		}
		return productId;
	}

	
	public List<Product> getProductsForSeller(int sellerId) {
		List<Product> products = null;
		Optional<Seller> seller = sellerRepository.findById(sellerId);
		if (seller.isPresent()) {
			products = productService.getProductForSeller(sellerId);
		}
		return products;
	}

	public void addProducts(int sellerId, List<Product> products) {
		Optional<Seller> seller=sellerRepository.findById(sellerId);
		if(seller.isPresent()) {
			productService.addProducts(seller.get(), products);
		}
	}
	
	
	
}
