package net.pluriel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.pluriel.dto.requests.FactureRequestDto;
import net.pluriel.dto.responses.FactureResponseDto;
import net.pluriel.services.FactureService;

@RestController
@RequestMapping("/factures")
public class FactureController {
	@Autowired
	private FactureService factureService;
	
	@PostMapping
	public ResponseEntity<FactureResponseDto> create(@RequestBody FactureRequestDto factureRequest){
		return new ResponseEntity<FactureResponseDto>(factureService.create(factureRequest), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FactureResponseDto> getOne(@PathVariable(name = "id") Integer factureId){
		return new ResponseEntity<FactureResponseDto>(factureService.getOne(factureId), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<FactureResponseDto> update(@PathVariable(name = "id") Integer factureId, @RequestBody FactureRequestDto factureRequest){
		return new ResponseEntity<FactureResponseDto>(factureService.update(factureRequest, factureId), HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Page<FactureResponseDto>> getAll(
			@RequestParam(name = "page") int page,
			@RequestParam(name = "size") int size
		){
		return new ResponseEntity<Page<FactureResponseDto>>(factureService.getAll(page, size), HttpStatus.OK);
	}
	
	@GetMapping("/all/{clientId}")
	public ResponseEntity<Page<FactureResponseDto>> getAllByClient(
			@PathVariable(name = "clientId") Integer clientId,
			@RequestParam(name = "page") int page,
			@RequestParam(name = "size") int size
		){
		return new ResponseEntity<Page<FactureResponseDto>>(factureService.getAllByClient(clientId, page, size), HttpStatus.OK);
	}
}
