package com.example.cafe.Controller;

import com.example.cafe.DTO.ProductDTO;
import com.example.cafe.Service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public String newProduct() {
        return "new-menu";
    }

    @GetMapping()
    public String menuAdminPage(Model model) {
        List<ProductDTO> products = productService.getAll();
        model.addAttribute("products", products);
        return "admin-menu";
    }
}
