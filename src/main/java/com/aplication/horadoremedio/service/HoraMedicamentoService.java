package com.aplication.horadoremedio.service;

import java.util.Optional;

import com.aplication.horadoremedio.model.entity.HoraMedicamento;

public interface HoraMedicamentoService {
	
	// salvar horaMedicamento na base de dados
	HoraMedicamento salvar(HoraMedicamento horaMedicamento);

	// exicluir da base de dados
	void deletar(HoraMedicamento horaMedicamento);
	
	// validar da base dados
	void validar(HoraMedicamento horaMedicamento);
	
	// buscar a horaMedicação por id
	Optional<HoraMedicamento> obterPorId(Long id);
}
