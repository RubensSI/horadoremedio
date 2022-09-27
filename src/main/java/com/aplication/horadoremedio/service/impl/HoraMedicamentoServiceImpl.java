package com.aplication.horadoremedio.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aplication.horadoremedio.exception.RegraNegocioException;
import com.aplication.horadoremedio.model.entity.HoraMedicamento;
import com.aplication.horadoremedio.model.repository.HoraMedicamentoRepository;
import com.aplication.horadoremedio.service.HoraMedicamentoService;

@Service
public class HoraMedicamentoServiceImpl implements HoraMedicamentoService {

	HoraMedicamentoRepository repository;

	public HoraMedicamentoServiceImpl(HoraMedicamentoRepository repository) {
		this.repository = repository;
	}

	@Override
	public HoraMedicamento salvar(HoraMedicamento horaMedicamento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(HoraMedicamento horaMedicamento) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validar(HoraMedicamento horaMedicamento) {
		
		// verificar se o medicameno está na base da dados.
		// se estivar trazo id do medicamento.
		// caso contrário retorna uma mensagem informando que o medicamewnto não esta presente.
		if (horaMedicamento.getIdMedicamento() == null || horaMedicamento.getIdMedicamento().getId() == null) {
			throw new RegraNegocioException("Informe o Medicamento.");
		}

		// verifica se o campo de descrisção não esta vazio ou se tem uma estring vazia
		// se sim retorna uma mensagem dizendo para informar uma mansagem válida.
		if (horaMedicamento.getDescricao() == null || horaMedicamento.getDescricao().trim().equals("")) {
			throw new RegraNegocioException("Informe uma Descrição válida.");
		}

	}

	// verifaca se o id do medicamento.
	// caso esteje retorna o id.
	// caso contrario pode retornar uma lista vazia.
	@Override
	public Optional<HoraMedicamento> obterPorId(Long id) {
		return repository.findById(id);
	}

}
