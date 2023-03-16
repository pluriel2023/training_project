package net.pluriel.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

import net.pluriel.dto.requests.ProductRequestDto;
import net.pluriel.dto.responses.ProductResponseDto;

public interface ProductService {
	public ProductResponseDto create(ProductRequestDto ProductRequestDto);
	public ProductResponseDto getOne(Integer id);
	public ProductResponseDto update(ProductRequestDto ProductRequestDto, Integer id);
	public List<ProductResponseDto> getAll();
	public Page<ProductResponseDto> getAllInPage(int page, int size);
	public  void delete(Integer productId);
}
