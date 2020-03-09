package com.example.assignment.service;

import java.util.List;

import com.example.assignment.model.Product;

public interface ProductService {
	List<Product> listAll();
	Product getProductDetails(Integer id);
}
