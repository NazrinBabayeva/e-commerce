package com.example.ecommerce.service;


import com.example.ecommerce.model.dto.BrandDto;
import com.example.ecommerce.model.entity.Brand;
import com.example.ecommerce.repository.BrandRepository;
import com.example.ecommerce.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandDto saveBrand(BrandDto dto) {
        Brand brand = Mapper.toBrandEntity(dto);
        brand.setStatus(true);
        return Mapper.toBrandDto(brandRepository.save(brand));
    }

    public List<BrandDto> getAllBrands() {
        return brandRepository.findAll()
                .stream()
                .filter(Brand::isStatus)
                .map(Mapper::toBrandDto)
                .collect(Collectors.toList());
    }

    public BrandDto getBrandById(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        if (!brand.isStatus()) throw new RuntimeException("Brand deleted");
        return Mapper.toBrandDto(brand);
    }

    public BrandDto getBrandByName(String name) {
        Brand brand = brandRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        if (!brand.isStatus()) throw new RuntimeException("Brand deleted");
        return Mapper.toBrandDto(brand);
    }

    public BrandDto updateBrand(BrandDto dto) {
        Brand brand = brandRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        brand.setName(dto.getName());
        brand.setDescription(dto.getDescription());
        return Mapper.toBrandDto(brandRepository.save(brand));
    }

    public void deleteBrand(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        brand.setStatus(false);
        brandRepository.save(brand);
    }
}
