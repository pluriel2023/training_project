package net.pluriel.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import net.pluriel.dto.requests.OrderRequestDto;
import net.pluriel.dto.responses.OrderResponseDto;
import net.pluriel.entities.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
	OrderMapper INSTANCE = Mappers.getMapper( OrderMapper.class );
	
	Order convertRequestToEntity(OrderRequestDto OrderRequest);
	
	Order convertResponseToEntity(OrderResponseDto OrderResponse);
	
	OrderResponseDto convertEntityToResponse(Order Order);
}
