
package com.example.ecommerce.controller;

import com.example.ecommerce.model.dto.BrandDto;
import com.example.ecommerce.model.dto.CategoryDto;
import com.example.ecommerce.model.dto.ProductDto;
import com.example.ecommerce.service.BrandService;
import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final CategoryService categoryService;
    private final BrandService brandService;
    private final ProductService productService;

    //CATEGORY
    @PostMapping("/category")
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody CategoryDto dto) {
        return ResponseEntity.ok(categoryService.saveCategory(dto));
    }

    @GetMapping("/category")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping("/category/search")
    public ResponseEntity<CategoryDto> getCategoryByName(@RequestParam String name) {
        return ResponseEntity.ok(categoryService.getCategoryByName(name));
    }

    @PutMapping("/category")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto dto) {
        return ResponseEntity.ok(categoryService.updateCategory(dto));
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

    // BRAND
    @PostMapping("/brand")
    public ResponseEntity<BrandDto> saveBrand(@RequestBody BrandDto dto) {
        return ResponseEntity.ok(brandService.saveBrand(dto));
    }

    @GetMapping("/brand")
    public ResponseEntity<List<BrandDto>> getAllBrands() {
        return ResponseEntity.ok(brandService.getAllBrands());
    }

    @GetMapping("/brand/{id}")
    public ResponseEntity<BrandDto> getBrandById(@PathVariable Long id) {
        return ResponseEntity.ok(brandService.getBrandById(id));
    }

    @GetMapping("/brand/search")
    public ResponseEntity<BrandDto> getBrandByName(@RequestParam String name) {
        return ResponseEntity.ok(brandService.getBrandByName(name));
    }

    @PutMapping("/brand")
    public ResponseEntity<BrandDto> updateBrand(@RequestBody BrandDto dto) {
        return ResponseEntity.ok(brandService.updateBrand(dto));
    }

    @DeleteMapping("/brand/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
        return ResponseEntity.ok().build();
    }

    //  PRODUCT
    @PostMapping("/product")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto dto) {
        return ResponseEntity.ok(productService.saveProduct(dto));
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/product/search")
    public ResponseEntity<ProductDto> getProductByName(@RequestParam String name) {
        return ResponseEntity.ok(productService.getProductByName(name));
    }

    @PutMapping("/product")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto dto) {
        return ResponseEntity.ok(productService.updateProduct(dto));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
