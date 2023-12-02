package com.empresa.entregadores.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EntregadorDto (	@NotBlank String cpf, 
						@NotNull String nome, 
						@NotNull String capacidade ) {
}
