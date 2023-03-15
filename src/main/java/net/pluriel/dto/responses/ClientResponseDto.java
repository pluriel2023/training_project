package net.pluriel.dto.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientResponseDto {
	private Integer id;
	private String name;
	private String email;
}
