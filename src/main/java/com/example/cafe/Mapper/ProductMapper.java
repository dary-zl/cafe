package com.example.cafe.Mapper;

import com.example.cafe.DTO.ProductDTO;
import com.example.cafe.DataBase.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductDTO dto);
    ProductDTO toDto(Product entity);
}
