package com.example.productapi.mapper;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.productapi.controller.ProductController;
import com.example.productapi.model.Product;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;

@Component
public class ProductModelAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {

    @Override
    public EntityModel<Product> toModel(Product product) {
        return EntityModel.of(product,
            linkTo(methodOn(ProductController.class).getProductById(product.getId())).withSelfRel(),
            linkTo(methodOn(ProductController.class).getAllProducts()).withRel("products"));
    }

    @Override
    public CollectionModel<EntityModel<Product>> toCollectionModel(Iterable<? extends Product> entities) {
        CollectionModel<EntityModel<Product>> productModels = RepresentationModelAssembler.super.toCollectionModel(entities);
        return productModels.add(linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
    }
}
