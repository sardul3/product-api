package com.example.productapi.mapper;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.productapi.controller.ProductController;
import com.example.productapi.model.Product;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.HashMap;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;

@Component
public class ProductModelAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {

    @Override
    public EntityModel<Product> toModel(Product product) {
        // Default empty parameters for the getAllProducts link
        HashMap<String, String> emptyParams = new HashMap<>();
        Pageable defaultPageable = PageRequest.of(0, 10);

        return EntityModel.of(product,
            linkTo(methodOn(ProductController.class).getProductById(product.getId())).withSelfRel(),
            linkTo(methodOn(ProductController.class).getAllProducts(emptyParams, defaultPageable))
                .withRel("products"));
    }

    @Override
    public CollectionModel<EntityModel<Product>> toCollectionModel(Iterable<? extends Product> entities) {
        // Default empty parameters for the getAllProducts link
        HashMap<String, String> emptyParams = new HashMap<>();
        Pageable defaultPageable = PageRequest.of(0, 10);

        CollectionModel<EntityModel<Product>> productModels = 
            RepresentationModelAssembler.super.toCollectionModel(entities);
        
        return productModels.add(
            linkTo(methodOn(ProductController.class)
                .getAllProducts(emptyParams, defaultPageable))
                .withSelfRel());
    }
}
