package net.pluriel.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import net.pluriel.entities.*;
import net.pluriel.exceptions.RestException;
import net.pluriel.repositories.ClientRepository;
import net.pluriel.repositories.PaymentRepository;
import net.pluriel.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import net.pluriel.dto.mappers.FactureMapper;
import net.pluriel.dto.requests.FactureRequestDto;
import net.pluriel.dto.responses.FactureResponseDto;
import net.pluriel.repositories.FactureRepository;
import net.pluriel.services.FactureService;

@Service
public class FactureServiceImpl implements FactureService{

	@Autowired
	private FactureMapper factureMapper;

	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private FactureRepository factureRepository;

	@SneakyThrows
	@Override
	@Transactional
	public FactureResponseDto create(FactureRequestDto factureRequestDto) {
		Facture facture = factureMapper.convertRequestToEntity(factureRequestDto);
		List<Order> orders = facture.getOrders();
		List<PaymentFacture> paymentFactures = facture.getPaymentFactures();
		facture.setOrders(new ArrayList<>());
		facture.setPaymentFactures(new ArrayList<>());
		factureRepository.save(facture);

		if (facture.getOrders() == null || facture.getOrders().isEmpty()) {
			throw new RestException("OrderList cannot be null or empty");
		}
		orders.stream().forEach(order -> {
			Client client = clientRepository.findById(order.getClient().getId())
					.orElseThrow(() -> new RestException("client not found"));
			Product product = productRepository.findById(order.getProduct().getId())
					.orElseThrow(() -> new RestException("Product not found"));
			order.setId(null);
			order.setQuantity(order.getQuantity());
			if (order.getQuantity() <= 0) {
				throw new RestException("Order quantity cannot be equal 0 - Must be greater than 0");
			}
			order.setFacture(facture);
		});
		if (facture.getPaymentFactures() == null || facture.getPaymentFactures().isEmpty()) {
			throw new RestException("PaymentInvoice List cannot be null or empty"); //Done
		}
		paymentFactures.stream().forEach(paymentFacture -> {
			Payment payment = paymentRepository.findById(paymentFacture.getPayment().getId())
					.orElseThrow(() -> new RestException("Payment not found")); //Done
			paymentFacture.setId(null);
			paymentFacture.setMontant(paymentFacture.getMontant());
		});
		facture.setOrders(orders);
		return factureMapper.convertEntityToResponse(facture);
	}



	@SneakyThrows
	@Override
	@Transactional
	public FactureResponseDto update(FactureRequestDto factureRequestDto, Integer id) {
		Facture facture = factureRepository.findById(id).orElseThrow(() -> new RestException("invoice not found"));
		Facture factureRequest = factureMapper.convertRequestToEntity(factureRequestDto);
		
		factureRequest.getOrders().stream().forEach(order -> {
			order.setFacture(facture);
		});

		factureRequest.getPaymentFactures().stream().forEach(paymentFacture -> {
			paymentFacture.setFacture(facture);
		});
		facture.setDatePaymentFacture(factureRequest.getDatePaymentFacture());
		facture.setOrders(factureRequest.getOrders());
		facture.setPaymentFactures(factureRequest.getPaymentFactures());
		
		factureRepository.save(facture);
		return factureMapper.convertEntityToResponse(facture);
	}

	@SneakyThrows
	@Override
	public FactureResponseDto getOne(Integer id) {
		Facture facture = factureRepository.findById(id).orElseThrow(() -> new Exception("Facture non trouvée"));
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
