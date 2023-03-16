package net.pluriel.dto.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderResponseDto {
	private Integer id;
    private int quantity;
    private ClientResponseDto client;
    private ProductResponseDto product;
}
