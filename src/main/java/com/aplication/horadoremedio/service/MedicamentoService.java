package com.aplication.horadoremedio.service;

import java.util.List;

import com.aplication.horadoremedio.model.entity.Medicamento;
import com.aplication.horadoremedio.model.enums.StatusMedicamento;

public interface MedicamentoService {

	Medicamento salvar(Medicamento medicamento);

	Medicamento atualizar(Medicamento medicamento);

	void deletar(Medicamento medicamento);

	List<Medicamento> buscar(Medicamento medicamentoFiltro);

	void atualizarStatus(Medicamento medicamento, StatusMedicamento status);

}
