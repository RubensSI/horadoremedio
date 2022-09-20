package com.aplication.horadoremedio.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aplication.horadoremedio.model.entity.Medicamento;
import com.aplication.horadoremedio.model.enums.StatusMedicamento;
import com.aplication.horadoremedio.model.repository.MedicamentoRepository;
import com.aplication.horadoremedio.service.MedicamentoService;

public class MedicamentoServiceImpl implements MedicamentoService {

	@Autowired
	private MedicamentoRepository repository;

	public MedicamentoServiceImpl(MedicamentoRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Medicamento salvar(Medicamento medicamento) {

		return repository.save(medicamento);
	}

	@Override
	@Transactional
	public Medicamento atualizar(Medicamento medicamento) {
		Objects.requireNonNull(medicamento.getId());
		return repository.save(medicamento);
	}

	@Override
	@Transactional
	public void deletar(Medicamento medicamento) {
		Objects.requireNonNull(medicamento.getId());
		repository.delete(medicamento);
	}

	@Override
	public List<Medicamento> buscar(Medicamento medicamentoFiltro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atualizarStatus(Medicamento medicamento, StatusMedicamento status) {
		medicamento.setStatus(status);
		atualizar(medicamento);

	}

}
