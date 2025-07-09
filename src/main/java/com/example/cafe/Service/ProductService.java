package com.example.cafe.Service;

import com.example.cafe.DTO.ProductDTO;
import com.example.cafe.DataBase.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    boolean save(ProductDTO productDTO);
    void save(Product product);
    List<ProductDTO> getAll();

    void deleteProduct(Long id);

    ProductDTO findById(Long id);
    void updateProduct(Long id, ProductDTO productDTO, MultipartFile imageFile) throws IOException;

    ProductDTO createProduct(ProductDTO productDTO, MultipartFile imageFile) throws IOException;
}
