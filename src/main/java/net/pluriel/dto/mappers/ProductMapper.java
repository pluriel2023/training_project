package net.pluriel.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import net.pluriel.dto.requests.ProductRequestDto;
import net.pluriel.dto.responses.ProductResponseDto;
import net.pluriel.entities.Product;


@Mapper(componentModel = "spring")
public interface ProductMapper {

	ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class );
	
	Product convertRequestToEntity(ProductRequestDto productRequest);
	
	Product convertResponseToEntity(ProductResponseDto productResponse);
	
	ProductResponseDto convertEntityToResponse(Product product);
}
