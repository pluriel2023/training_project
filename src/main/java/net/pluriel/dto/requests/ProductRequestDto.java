package net.pluriel.dto.requests;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequestDto {

	private String libelle;
	 private BigDecimal price;
}
