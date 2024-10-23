package com.example.productapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.productapi.dto.ProductDto;
import com.example.productapi.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto productToProductDto(Product product);
    
    @Mapping(target = "id", ignore = true)
    Product productDtoToProduct(ProductDto productDto);
}
