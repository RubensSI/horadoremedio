package com.aplication.horadoremedio.api.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicamentoDTO {
	private Long id;
	private String nome;
	private String descricao;
	private Long usuario;
	private LocalDate dataCadastro;
	private String tipo;
	private String status;
	
	
}
