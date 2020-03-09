package com.example.assignment.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.model.Product;
import com.example.assignment.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
	@Autowired
	private ProductService service;
	
	@GetMapping("/products")
	public List<Product> getProductList() {
		return service.listAll();
	}
	
	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable Integer id) {
		return service.getProductDetails(id);
	}
	
	@PostMapping("/products")
	@ResponseStatus(HttpStatus.CREATED)
	public Product addProduct(@Valid @RequestBody Product product) {
		return service.addProduct(product);
	}
	
	@PutMapping("/products/{id}")
	 public Product updateProduct(@RequestBody Product product, @PathVariable Integer id) {
		Product updateProduct = service.getProductDetails(id);
		updateProduct.setName(product.getName());
		updateProduct.setCurrentPrice(product.getCurrentPrice());
		updateProduct.setLastUpdate(new Date());
		return service.addProduct(updateProduct);     
	 }
}
