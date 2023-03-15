package net.pluriel.services;

import java.util.List;

import org.springframework.data.domain.Page;

import net.pluriel.dto.requests.ClientRequestDto;
import net.pluriel.dto.responses.ClientResponseDto;

public interface ClientService {
	public ClientResponseDto create(ClientRequestDto clientRequestDto);
	public ClientResponseDto getOne(Integer id);
	public ClientResponseDto update(ClientRequestDto clientRequestDto, Integer id);
	public List<ClientResponseDto> getAll();
	public Page<ClientResponseDto> getAllInPage(int page, int size);
}
