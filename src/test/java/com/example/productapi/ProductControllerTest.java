package com.example.productapi;

import com.example.productapi.dto.ProductDto;
import com.example.productapi.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	ProductRepository productRepository;

	@BeforeEach
	void setup() {
		productRepository.deleteAll();
	}

	@Test
	void getAllProductsShouldReturnOkTest() throws Exception {
		mockMvc.perform(
				get("/api/products")
		).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		;
	}

	@Test
	void createProductShouldReturnCreatedTest() throws Exception {
		ProductDto productDto = new ProductDto("name", "desc", new BigDecimal("100.99"));
		mockMvc.perform(
				post("/api/products")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(productDto))

		).andExpect(status().isCreated());
	}

	@Test
	void createProductShouldCreateNewObjectInDB() throws Exception {
		ProductDto productDto = new ProductDto("name", "desc", new BigDecimal("100.99"));
		mockMvc.perform(
				post("/api/products")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(productDto))

		).andExpect(status().isCreated());
		assertThat(productRepository.findAll()).hasSize(1);
	}

}
