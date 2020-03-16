package com.example.assignment.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.assignment.exception.ProductNotFoundException;
import com.example.assignment.model.Product;
import com.example.assignment.service.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	private List<Product> productList;
	
	@MockBean
	ProductService productService;

	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() {
	    mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext).build();
		productList = Arrays.asList(
				new Product("milk", new BigDecimal("28.9")));
	}

	
	@After
	public void tearDown() {
		productList = null;
	}
	
	@WithMockUser
	@Test
	public void getProductList_ShouldReturnProductList() throws Exception {
		Mockito.when(productService.listAll()).thenReturn(productList);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
		.andExpect(status().isOk());
	}
	
	@WithMockUser
	@Test
	public void getProduct_ShouldReturnProduct() throws Exception {
		Mockito.when(productService.getProductDetails(Mockito.anyInt())).thenReturn(new Product("milk",new BigDecimal("28.9")));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/products/2"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("name").value("milk"))
		.andExpect(jsonPath("current_price").value(28.9));
	}
	
	@WithMockUser
	@Test
	public void getProduct_NotFound() throws Exception {
		Mockito.when(productService.getProductDetails(Mockito.anyInt())).thenThrow(new ProductNotFoundException());
	
		mockMvc.perform(MockMvcRequestBuilders.get("/api/products/2"))
		.andExpect(status().isNotFound());
	}
	
	@WithMockUser
	@Test
	public void addProduct_ShouldCreateProduct() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
				.content("{\"name\" : \"milk\",\"current_price\" : 29.9}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@WithMockUser
	@Test
	public void updateProduct_ShouldUpdateExistingProduct() throws Exception{
		Mockito.when(productService.getProductDetails(Mockito.anyInt())).thenReturn(new Product("milk",new BigDecimal("28.9")));
		
		mockMvc.perform(MockMvcRequestBuilders.put("/api/products/1")
				.content("{\"name\" : \"milk\",\"current_price\" : 25.4}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
}
