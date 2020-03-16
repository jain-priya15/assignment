package com.example.assignment.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.assignment.model.Product;
import com.example.assignment.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class IntegrationTest {
	
	@Autowired
	private TestRestTemplate  restTemplate;
	
	@Autowired
	ProductRepository productRepository;
	
	@Before
	public void setUp() throws Exception{
        Mockito.when(productRepository.findById(1)).thenReturn(new Product("milk",new BigDecimal("27.5")));
	}

	@Test
	void getProduct_returnProductDetails() throws Exception{
		//act
		ResponseEntity<Product> response =restTemplate
				.getForEntity("/api/products/1", Product.class);
		
		//assert
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
	}

}

