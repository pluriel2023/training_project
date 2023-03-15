package net.pluriel.services.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.pluriel.dto.mappers.ProductMapper;
import net.pluriel.dto.requests.ProductRequestDto;
import net.pluriel.dto.responses.ClientResponseDto;
import net.pluriel.dto.responses.ProductResponseDto;
import net.pluriel.entities.Client;
import net.pluriel.entities.Product;
import net.pluriel.repositories.ProductRepository;
import net.pluriel.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service @Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductResponseDto create(ProductRequestDto ProductRequestDto) {
        Product product = productMapper.convertRequestToEntity(ProductRequestDto);
        productRepository.save(product);
        return productMapper.convertEntityToResponse(product);
    }

    @SneakyThrows
    @Override
    public ProductResponseDto getOne(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new Exception("Not Found a bana"));
        return productMapper.convertEntityToResponse(product);
    }

    @SneakyThrows
    @Override
    public ProductResponseDto update(ProductRequestDto productRequestDto, Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new Exception("Not found"));
        Product productRequest = productMapper.convertRequestToEntity(productRequestDto);
        product.setLibelle(productRequest.getLibelle());
        product.setPrice(productRequest.getPrice());
        productRepository.save(product);
        return productMapper.convertEntityToResponse(product);
    }

    @Override
    public List<ProductResponseDto> getAll() {
        List<Product> allProduct = productRepository.findAll();
        List<ProductResponseDto> responseDtos = allProduct.stream().map(productMapper::convertEntityToResponse).collect(Collectors.toList());
        return responseDtos;
    }

    @Override
    public Page<ProductResponseDto> getAllInPage(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size)).map(productMapper::convertEntityToResponse);
    }
}
