package com.aplication.horadoremedio.api.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HoraMedicamentoDTO {
	private Long id;
	private String descricao;
	private Date hora;
	private Long Medicamento;
}
