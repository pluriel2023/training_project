package net.pluriel.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.pluriel.dto.mappers.ClientMapper;
import net.pluriel.dto.requests.ClientRequestDto;
import net.pluriel.dto.responses.ClientResponseDto;
import net.pluriel.entities.Client;
import net.pluriel.exceptions.NotFound;
import net.pluriel.exceptions.RestException;
import net.pluriel.repositories.ClientRepository;
import net.pluriel.services.ClientService;

@Service @Slf4j
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientMapper clientMapper;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public ClientResponseDto create(ClientRequestDto clientRequestDto) {
		Client clientRequest = clientMapper.convertRequestToEntity(clientRequestDto);
		
		Optional<Client> ClientOptional = clientRepository.findByName(clientRequest.getName());
        if (ClientOptional.isPresent()) {
            throw new RestException("Client with name '" + clientRequest.getName() + "' already exists");
        }
		
		clientRepository.save(clientRequest);
		return clientMapper.convertEntityToResponse(clientRequest);
	}

	@SneakyThrows
	@Override
	public ClientResponseDto getOne(Integer id){
		Client client = clientRepository.findById(id).orElseThrow(() -> new Exception("Not Found"));
//		Optional<Client> clientOpt = clientRepository.findById(id);
//		Client client = null;
//		if(clientOpt.isPresent()) {
//			client = clientOpt.get();
//		}else {
//			throw new Exception("Not Found");
//		}
		return clientMapper.convertEntityToResponse(client);
	}

	@SneakyThrows
	@Override
	public ClientResponseDto update(ClientRequestDto clientRequestDto, Integer id) {
		Client client = clientRepository.findById(id).orElseThrow(() -> new Exception("Not Found"));
		Client clientRequest = clientMapper.convertRequestToEntity(clientRequestDto);
		
		client.setName(clientRequest.getName());
		client.setEmail(clientRequest.getEmail());
		
		clientRepository.save(client);
		
		return clientMapper.convertEntityToResponse(client);
	}

	@Override
	public List<ClientResponseDto> getAll() {
		List<Client> all = clientRepository.findAll();
		List<ClientResponseDto> result = all.stream().map(clientMapper::convertEntityToResponse).collect(Collectors.toList());
		return result;
	}

	@Override
	public Page<ClientResponseDto> getAllInPage(int page, int size) {
		return clientRepository.findAll(PageRequest.of(page, size)).map(clientMapper::convertEntityToResponse);
	}
	@SneakyThrows
	@Override
	public void delete(Integer clientId) {
		// TODO Auto-generated method stub
		Client client = clientRepository.findById(clientId).orElseThrow(() -> new Exception("Not Found"));
		clientRepository.delete(client);
		
	}
	
}
