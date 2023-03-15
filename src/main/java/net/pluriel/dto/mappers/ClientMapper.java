package net.pluriel.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import net.pluriel.dto.requests.ClientRequestDto;
import net.pluriel.dto.responses.ClientResponseDto;
import net.pluriel.entities.Client;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface ClientMapper {

	ClientMapper INSTANCE = Mappers.getMapper( ClientMapper.class );
	
	Client convertRequestToEntity(ClientRequestDto clientRequest);
	
	Client convertResponseToEntity(ClientResponseDto clientResponse);
	
	ClientResponseDto convertEntityToResponse(Client client);
	
}
