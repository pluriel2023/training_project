package net.pluriel.dto.responses;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.pluriel.entities.Client;
import net.pluriel.entities.Product;

@Data
@NoArgsConstructor
public class OrderResponseDto {
	private Integer id;
    private int quantity;
    private Date dateOrder;
    private Client client;
    private Product product;
}
