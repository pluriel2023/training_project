package net.pluriel.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.pluriel.dto.mappers.ProductMapper;
import net.pluriel.dto.requests.ProductRequestDto;
import net.pluriel.dto.responses.ProductResponseDto;
import net.pluriel.entities.Product;
import net.pluriel.repositories.ProductRepository;
import net.pluriel.services.ProductService;


@Service @Slf4j
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public ProductResponseDto create(ProductRequestDto ProductRequestDto) {
		Product ProductRequest = productMapper.convertRequestToEntity(ProductRequestDto);
		productRepository.save(ProductRequest);
		return productMapper.convertEntityToResponse(ProductRequest);
	}

	@SneakyThrows
	@Override
	public ProductResponseDto getOne(Integer id) {
		// TODO Auto-generated method stub
		Product product=productRepository.findById(id).orElseThrow(()->new Exception("product not found"));
		return productMapper.convertEntityToResponse(product);
	}

	@SneakyThrows
	@Override
	public ProductResponseDto update(ProductRequestDto ProductRequestDto, Integer id) {
		Product product=productRepository.findById(id).orElseThrow(()->new Exception("product not found"));
		Product productRequest=productMapper.convertRequestToEntity(ProductRequestDto);
		product.setLibelle(productRequest.getLibelle());
		product.setPrice(productRequest.getPrice());
		productRepository.save(product);
		return productMapper.convertEntityToResponse(product);
	}

	@SneakyThrows
	@Override
	public List<ProductResponseDto> getAll() {
		// TODO Auto-generated method stub
		List<Product> All=productRepository.findAll();
		List<ProductResponseDto> results=All.stream().map(productMapper::convertEntityToResponse).collect(Collectors.toList());
		return results;
	}


	@Override
	public Page<ProductResponseDto> getAllInPage(int page, int size) {
		// TODO Auto-generated method stub
		return productRepository.findAll(PageRequest.of(page, size)).map(productMapper::convertEntityToResponse);
	}

	@SneakyThrows
	@Override
	public  void delete(Integer productId) {
		// TODO Auto-generated method stub
		Product product=productRepository.findById(productId).orElseThrow(()->new Exception("product not found"));
		productRepository.delete(product);
		
	}
}
