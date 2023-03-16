package net.pluriel.dto.requests;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderRequestDto {
	private Integer id;
	private int quantity;
    private RequestIdDto client;
    private RequestIdDto product;
}
