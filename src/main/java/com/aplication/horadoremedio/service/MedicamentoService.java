package com.aplication.horadoremedio.service;

import java.util.List;
import java.util.Optional;

import com.aplication.horadoremedio.model.entity.Medicamento;
import com.aplication.horadoremedio.model.enums.StatusMedicamento;

public interface MedicamentoService {

	Medicamento salvar(Medicamento medicamento);

	Medicamento atualizar(Medicamento medicamento);

	void deletar(Medicamento medicamento);

	List<Medicamento> buscar(Medicamento medicamentoFiltro);

	void atualizarStatus(Medicamento medicamento, StatusMedicamento status);
	
	void validar (Medicamento medicamento);
	
	public void validarNomeMedicameto(String nome);
	
	Optional<Medicamento> obterPorId(Long id);

}
