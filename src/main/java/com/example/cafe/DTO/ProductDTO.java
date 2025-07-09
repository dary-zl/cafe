package com.example.cafe.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private Long id;
    private Integer price;
    private String name;
    private String category;
    private String subcategory;
    private String imagePath;
    private MultipartFile imageFile;
    private String info;
}
