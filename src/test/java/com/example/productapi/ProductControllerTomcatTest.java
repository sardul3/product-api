package com.example.productapi;

import com.example.productapi.dto.ProductDto;
import com.example.productapi.model.Product;
import com.example.productapi.repository.ProductRepository;
import com.example.productapi.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ProductControllerTomcatTest {

    @LocalServerPort
    int port;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @BeforeEach
    void setup() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setDescription("Product 1");

        List<Product> products = List.of(product);
        Page<Product> productPage = new PageImpl<>(products, PageRequest.of(0, 10), products.size());

        when(productService.getFilteredProducts(any(), any())).thenReturn(productPage);
        when(productService.getProductById(any())).thenReturn(product);
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
    void getAllProductsShouldHaveEmbeddedElement() {
        RestClient client = RestClient.create();
        String response = client.get().uri(
                "http://localhost:" + port + "/api/products")
                .retrieve()
                .body(String.class);

        assertThat(Optional.ofNullable(JsonPath.parse(response).read("$._embedded"))).isNotNull();
    }

    @Test
    void getProductShouldReturnCorrectIdAndLinks() throws Exception {
        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$._links.self.href").exists())
                .andExpect(jsonPath("$._links.products.href").exists());
    }

}
