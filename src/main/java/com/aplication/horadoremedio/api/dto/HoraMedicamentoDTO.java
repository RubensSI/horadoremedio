package com.aplication.horadoremedio.api.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HoraMedicamentoDTO {
	
	private Long id;
	private String descricao;
	private Date dataHora;
	private Long idMedicamento;
}
