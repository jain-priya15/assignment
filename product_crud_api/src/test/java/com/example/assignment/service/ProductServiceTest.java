package com.example.assignment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.assignment.exception.ProductNotFoundException;
import com.example.assignment.model.Product;
import com.example.assignment.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
	
	@Mock
	private ProductRepository productRespository;
	
	private ProductServiceImpl productService;

	private List<Product> productList;
	
	@Before
	public void setUp() throws Exception{
		productService = new ProductServiceImpl(productRespository);
		productList = Arrays.asList(
				new Product("milk", new BigDecimal("28.9")));
	}
	
	@After
	public void tearDown() {
		productService= null;
	}
	
	@Test
	public void listAll_returnAllProductList() {
		//arrange
		Mockito.when(productRespository.findAll()).thenReturn(productList);
		//act
		List<Product> productAllList = productService.listAll();
		//assert
		assertThat(productAllList).isEqualTo(productList);
	}
	
	@Test
	public void getProductDetails_returnProductDetails() {
		//arrange
		when(productRespository.findById(Mockito.anyInt())).thenReturn(new Product("milk", new BigDecimal("28.9")));
		//act
		Product product = productService.getProductDetails(Mockito.anyInt());
		//assert
		assertThat(product.getName()).isEqualTo("milk");
		assertThat(product.getCurrentPrice()).isEqualTo("28.9");
	}
	
	@Test(expected = ProductNotFoundException.class)
	public void getProductDetails_whenNotFound() throws Exception{
		//arrange
		when(productRespository.findById(Mockito.anyInt())).thenReturn(null);
		//act
		productService.getProductDetails(Mockito.anyInt());			
	}
	
	@Test
	public void addProduct_returnProduct() {
		//arrange
		when(productRespository.save(Mockito.any(Product.class))).thenReturn(new Product("bread", new BigDecimal("15.5")));
		//act
		Product product = productService.addProduct(new Product("bread", new BigDecimal("15.5")));
		//assert
		assertThat(product.getName()).isEqualTo("bread");
		assertThat(product.getCurrentPrice()).isEqualTo("15.5");
	}

}
