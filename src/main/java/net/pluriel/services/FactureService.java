package net.pluriel.services;

import org.springframework.data.domain.Page;

import net.pluriel.dto.requests.FactureRequestDto;
import net.pluriel.dto.responses.FactureResponseDto;

public interface FactureService {
	public FactureResponseDto create(FactureRequestDto factureRequestDto);
	public FactureResponseDto getOne(Integer id);
	public FactureResponseDto update(FactureRequestDto factureRequestDto, Integer id);
	public Page<FactureResponseDto> getAll(int page, int size);
	public Page<FactureResponseDto> getAllByClient(Integer clientId, int page, int size);
}
