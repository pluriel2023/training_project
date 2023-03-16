package net.pluriel.dto.requests;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.pluriel.entities.Client;
import net.pluriel.entities.Product;
@Data
@NoArgsConstructor
public class OrderRequestDto {
	private int quantity;
    private Date dateOrder;
    private Integer clientId;
    private Integer productId;
}
