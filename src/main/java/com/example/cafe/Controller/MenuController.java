package com.example.cafe.Controller;

import com.example.cafe.DAO.ProductRepository;
import com.example.cafe.DTO.ProductDTO;
import com.example.cafe.DataBase.Product;
import com.example.cafe.DataBase.Reservation;
import com.example.cafe.Service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    public MenuController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping
    public String menuPage() {
        return "menu";
    }

    @GetMapping("/{category}")
    public String getMenuByCategory(
            @PathVariable String category,
            Model model) {

        String categoryName = switch(category) {
            case "drinks" -> "Напитки";
            case "food" -> "Еда";
            case "special" -> "Специальные предложения";
            default -> category;
        };

        model.addAttribute("category", categoryName);
        model.addAttribute("subcategories",
                productRepository.findSubcategoriesByCategory(category));
        model.addAttribute("products",
                productRepository.findByCategory(category));

        return "menu-food";
    }

    @GetMapping(value = "/api/{category}", produces = "application/json")
    @ResponseBody
    public List<Product> getProductsByCategoryApi(@PathVariable String category) {
        return productRepository.findByCategory(category);
    }

    @GetMapping("/categories")
    @ResponseBody
    public List<String> getAllCategories() {
        return productRepository.findAllDistinctCategories();
    }

    @GetMapping("/{category}/subcategories")
    @ResponseBody
    public List<String> getSubcategories(@PathVariable String category) {
        return productRepository.findSubcategoriesByCategory(category);
    }

    @GetMapping("/{category}/{subcategory}")
    @ResponseBody
    public List<Product> getProductsBySubcategory(
            @PathVariable String category,
            @PathVariable String subcategory) {
        return productRepository.findByCategoryAndSubcategory(category, subcategory);
    }


    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        ProductDTO productDTO = productService.findById(id);
        model.addAttribute("product", productDTO);
        return "edit-menu";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(
            @PathVariable Long id,
            @ModelAttribute("product") ProductDTO productDTO,
            @RequestParam("imageFile") MultipartFile imageFile,
            RedirectAttributes redirectAttributes) throws IOException {
        productService.updateProduct(id, productDTO, imageFile);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin";
    }

    @PostMapping("/add")
    public String addProduct(
            @ModelAttribute ProductDTO productDTO,
            @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        productService.createProduct(productDTO, imageFile);
        return "redirect:/admin";
    }
}