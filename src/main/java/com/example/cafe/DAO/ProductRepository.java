package com.example.cafe.DAO;

import com.example.cafe.DataBase.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Все товары по категории
    List<Product> findByCategory(String category);

    // Все товары по категории и подкатегории
    List<Product> findByCategoryAndSubcategory(String category, String subcategory);

    // Все уникальные категории
    @Query("SELECT DISTINCT p.category FROM Product p")
    List<String> findAllDistinctCategories();

    // Все подкатегории для выбранной категории
    @Query("SELECT DISTINCT p.subcategory FROM Product p WHERE p.category = :category")
    List<String> findSubcategoriesByCategory(@Param("category") String category);
}
