package net.pluriel.dto.requests;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientRequestDto {
	private String name;
	private String email;
}
