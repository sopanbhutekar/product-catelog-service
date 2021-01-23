package com.productcatalogservice.repository;


import org.springframework.data.repository.CrudRepository;
import com.productcatalogservice.model.Seller;

public interface SellerRepository extends CrudRepository<Seller, Integer>{
}
