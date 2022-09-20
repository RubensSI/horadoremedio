package com.aplication.horadoremedio.service.impl;

import java.util.List;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Medicamento atualizar(Medicamento medicamento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(Medicamento medicamento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Medicamento> buscar(Medicamento medicamentoFiltro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atualizarStatus(Medicamento medicamento, StatusMedicamento status) {
		// TODO Auto-generated method stub
		
	}

}
