package com.example.assignment.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.assignment.repository.ProductRepository;
import com.example.assignment.exception.ProductNotFoundException;
import com.example.assignment.model.Product;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
    private ProductRepository productRepository;
     
	public ProductServiceImpl(ProductRepository productRespository) {
		this.productRepository = productRespository;
	}

	@Override
    public List<Product> listAll() {
        return productRepository.findAll();
    }

	@Override
	public Product getProductDetails(Integer id) {
		Product product = productRepository.findById(id);
		if(product == null) {
			throw new ProductNotFoundException();
		}
		return product;
	}

	@Override
	public Product addProduct(Product product) {
    	product.setLastUpdate(new Date());
    	return productRepository.save(product);
	}
    
}
