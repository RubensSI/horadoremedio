package com.aplication.horadoremedio.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicamentoDTO {
	private Long id;
	private String nome;
	private String decricao;
	private Long usuario;
	private String tipo;
	private String status;
	
	
}
