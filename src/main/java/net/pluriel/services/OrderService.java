package net.pluriel.services;

import org.springframework.data.domain.Page;

import net.pluriel.dto.responses.OrderResponseDto;

public interface OrderService {
	public Page<OrderResponseDto> getAllInPage(int page, int size);
	public Page<OrderResponseDto> getAllByClient(Integer clientId, int page, int size);
}
