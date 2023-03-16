package net.pluriel.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import net.pluriel.dto.mappers.FactureMapper;
import net.pluriel.dto.requests.FactureRequestDto;
import net.pluriel.dto.responses.FactureResponseDto;
import net.pluriel.entities.Facture;
import net.pluriel.entities.Order;
import net.pluriel.repositories.FactureRepository;
import net.pluriel.services.FactureService;

@Service
public class FactureServiceImpl implements FactureService{

	@Autowired
	private FactureMapper factureMapper;
	
	@Autowired
	private FactureRepository factureRepository;
	
	@Override
	@Transactional
	public FactureResponseDto create(FactureRequestDto factureRequestDto) {
		Facture facture = factureMapper.convertRequestToEntity(factureRequestDto);
		List<Order> orders = facture.getOrders();
		facture.setOrders(new ArrayList<>());
		
		factureRepository.save(facture);
		
		orders.stream().forEach(order -> {
			order.setId(null);
			// ---------------------- Check ClientId, productId ------------
			order.setFacture(facture);
		});
		
		facture.setOrders(orders);
		
		return factureMapper.convertEntityToResponse(facture);
	}

	@SneakyThrows
	@Override
	public FactureResponseDto getOne(Integer id) {
		Facture facture = factureRepository.findById(id).orElseThrow(() -> new Exception("Facture non trouvée"));
		return factureMapper.convertEntityToResponse(facture);
	}

	@SneakyThrows
	@Override
	@Transactional
	public FactureResponseDto update(FactureRequestDto factureRequestDto, Integer id) {
		Facture facture = factureRepository.findById(id).orElseThrow(() -> new Exception("Facture non trouvée"));
		Facture factureRequest = factureMapper.convertRequestToEntity(factureRequestDto);
		
		factureRequest.getOrders().stream().forEach(order -> {
			// ---------------------- Check ClientId, productId ------------
			order.setFacture(facture);
		});
		
		facture.setDatePaymentFacture(factureRequest.getDatePaymentFacture());
		facture.setOrders(factureRequest.getOrders());
		
		factureRepository.save(facture);
		
		return factureMapper.convertEntityToResponse(facture);
	}

	@Override
	public Page<FactureResponseDto> getAll(int page, int size) {
		return factureRepository.findAll(PageRequest.of(page, size)).map(factureMapper::convertEntityToResponse);
	}

	@Override
	public Page<FactureResponseDto> getAllByClient(Integer clientId, int page, int size) {
		return factureRepository.findAll(PageRequest.of(page, size)).map(factureMapper::convertEntityToResponse);
	}

}
