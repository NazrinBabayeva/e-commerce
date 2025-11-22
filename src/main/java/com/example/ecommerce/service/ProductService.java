
package com.example.ecommerce.service;

import com.example.ecommerce.model.dto.ProductDto;
import com.example.ecommerce.model.entity.Brand;
import com.example.ecommerce.model.entity.Category;
import com.example.ecommerce.model.entity.Product;
import com.example.ecommerce.repository.BrandRepository;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    public ProductDto saveProduct(ProductDto dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Brand brand = brandRepository.findById(dto.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        Product product = Mapper.toProductEntity(dto, category, brand);
        return Mapper.toProductDto(productRepository.save(product));
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .filter(Product::isStatus)
                .map(Mapper::toProductDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getAllActiveProducts() {
        return productRepository.findByStatusTrue()
                .stream()
                .map(Mapper::toProductDto)
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (!product.isStatus()) throw new RuntimeException("Product deleted");
        return Mapper.toProductDto(product);
    }

    public ProductDto getProductByName(String name) {
        Product product = productRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (!product.isStatus()) throw new RuntimeException("Product deleted");
        return Mapper.toProductDto(product);
    }

    public ProductDto updateProduct(ProductDto dto) {
        Product product = productRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setAmount(dto.getAmount());
        product.setRemainCount(dto.getRemainCount());
        // Update category/brand if provided
        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        }
        if (dto.getBrandId() != null) {
            Brand brand = brandRepository.findById(dto.getBrandId())
                    .orElseThrow(() -> new RuntimeException("Brand not found"));
            product.setBrand(brand);
        }
        return Mapper.toProductDto(productRepository.save(product));
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setStatus(false); // Soft delete
        productRepository.save(product);
    }
}
