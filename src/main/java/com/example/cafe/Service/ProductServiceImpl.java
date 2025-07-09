package com.example.cafe.Service;

import com.example.cafe.DAO.ProductRepository;
import com.example.cafe.DTO.ProductDTO;
import com.example.cafe.DataBase.Product;
import com.example.cafe.Mapper.ProductMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Value("${file.upload-dir}")
    private String uploadDir;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public boolean save(ProductDTO productDTO) {
        Product product = Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .category(productDTO.getCategory())
                .subcategory(productDTO.getSubcategory())
                .imagePath(productDTO.getImagePath())
                .info(productDTO.getInfo())
                .build();
        this.save(product);
        return true;
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO, MultipartFile imageFile) throws IOException {
        String imagePath = processUploadedImage(imageFile);

        Product product = productMapper.toEntity(productDTO);
        product.setImagePath(imagePath);

        Product savedProduct = productRepository.save(product);

        return productMapper.toDto(savedProduct);
    }

    private String processUploadedImage(MultipartFile imageFile) throws IOException {
        if (imageFile == null || imageFile.isEmpty()) {
            return null;
        }

        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        String fileName = UUID.randomUUID() + "_" + StringUtils.cleanPath(imageFile.getOriginalFilename());

        String fullPath = uploadDir + fileName;

        imageFile.transferTo(new File(fullPath));

        return "image/uploads/" + fileName;
    }


    @Override
    public List<ProductDTO> getAll() {
        return productRepository.findAll().stream()
                .map(this::toDto)
                .collect((Collectors.toList()));
    }

    private ProductDTO toDto(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .category(product.getCategory())
                .subcategory(product.getSubcategory())
                .info(product.getInfo())
                .imagePath(product.getImagePath())
                .build();
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDTO findById(Long id) {
        return productRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    @Override
    public void updateProduct(Long id, ProductDTO productDTO, MultipartFile imageFile) throws IOException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setCategory(productDTO.getCategory());
        product.setSubcategory(productDTO.getSubcategory());
        product.setInfo(productDTO.getInfo());

        if (imageFile != null && !imageFile.isEmpty()) {
            String newImagePath = processUploadedImage(imageFile);
            product.setImagePath(newImagePath);
        }

        productRepository.save(product);
    }

}
