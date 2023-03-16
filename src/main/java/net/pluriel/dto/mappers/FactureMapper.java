package net.pluriel.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import net.pluriel.dto.requests.FactureRequestDto;
import net.pluriel.dto.responses.FactureResponseDto;
import net.pluriel.entities.Facture;

@Mapper(componentModel = "spring")
public interface FactureMapper {

	FactureMapper INSTANCE = Mappers.getMapper(FactureMapper.class);
	
	Facture convertRequestToEntity(FactureRequestDto factureRequest);
	
	Facture convertResponseToEntity(FactureResponseDto factureResponse);
	
	FactureResponseDto convertEntityToResponse(Facture facture);
}
