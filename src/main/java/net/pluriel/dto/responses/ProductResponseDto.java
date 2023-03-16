package net.pluriel.dto.responses;

import java.math.BigDecimal;

import javax.persistence.Column;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductResponseDto {

	 private Integer id;
	 private String libelle;
	 private BigDecimal price;
}
