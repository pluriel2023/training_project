package net.pluriel.services;

import net.pluriel.dto.requests.ProductRequestDto;
import net.pluriel.dto.responses.ProductResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    public ProductResponseDto create(ProductRequestDto ProductRequestDto);
    public ProductResponseDto getOne(Integer id);
    public ProductResponseDto update(ProductRequestDto productRequestDto, Integer id);
    public List<ProductResponseDto> getAll();
    public Page<ProductResponseDto> getAllInPage(int page, int size);
}
