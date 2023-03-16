package net.pluriel.services;

import java.util.List;

import org.springframework.data.domain.Page;

import net.pluriel.dto.requests.OrderRequestDto;
import net.pluriel.dto.responses.OrderResponseDto;

public interface OrderService {
	public OrderResponseDto create(OrderRequestDto OrderRequestDto);
	public OrderResponseDto getOne(Integer id);
	public OrderResponseDto update(OrderRequestDto OrderRequestDto, Integer id);
	public List<OrderResponseDto> getAll();
	public Page<OrderResponseDto> getAllInPage(int page, int size);
	public void delete(Integer OrderId);
}
