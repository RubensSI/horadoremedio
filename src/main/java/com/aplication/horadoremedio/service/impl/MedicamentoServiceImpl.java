package com.aplication.horadoremedio.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aplication.horadoremedio.exception.RegraNegocioException;
import com.aplication.horadoremedio.model.entity.Medicamento;
import com.aplication.horadoremedio.model.enums.StatusMedicamento;
import com.aplication.horadoremedio.model.repository.MedicamentoRepository;
import com.aplication.horadoremedio.service.MedicamentoService;

import lombok.val;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

	private MedicamentoRepository repository;

	public MedicamentoServiceImpl(MedicamentoRepository repository) {
		this.repository = repository;
	}

	// abri uma transação para salvar o usuario na base da dados
	// varificar se o usario está com todos dados carretos para depois commitar
	// os dados caso comtrário faz o rollback da transação.
	// salvar um novo usuario na base de dados e setar o status como pendente
	@Override
	@Transactional
	public Medicamento salvar(Medicamento medicamento) {
		validar(medicamento);
		validarNomeMedicameto(medicamento.getNome());
		medicamento.setStatus(StatusMedicamento.PENDENTE);
		return repository.save(medicamento);
	}

	// garantir que a transação aconteça de forma correta
	// caso isso não ocorra fazer rollback da transação
	// atualizar um medicamento pelo
	// garantir que o id não vai vir nulo
	// caso contrário salva como um novo usuário.
	@Override
	@Transactional
	public Medicamento atualizar(Medicamento medicamento) {
		Objects.requireNonNull(medicamento.getId());
		validar(medicamento);
		return repository.save(medicamento);
	}

	@Override
	@Transactional
	public void deletar(Medicamento medicamento) {
		Objects.requireNonNull(medicamento.getId());
		repository.delete(medicamento);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Medicamento> buscar(Medicamento medicamentoFiltro) {
		Example examle = Example.of(medicamentoFiltro,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return repository.findAll(examle);
	}

	@Override
	public void atualizarStatus(Medicamento medicamento, StatusMedicamento status) {
		medicamento.setStatus(status);
		atualizar(medicamento);

	}

	@Override
	public void validar(Medicamento medicamento) {

		if (medicamento.getNome() == null || medicamento.getNome().trim().equals("")) {
			throw new RegraNegocioException("Informe o Nome do medicamento");
		}

//		if (medicamento.getDescricao() == null || medicamento.getDescricao().trim().equals("")) {
//			throw new RegraNegocioException("Informe uma Descrição válida.");
//		}

		if (medicamento.getUsuario() == null || medicamento.getUsuario().getId() == null) {
			throw new RegraNegocioException("Informe o Usuário.");
		}

		if (medicamento.getTipo() == null) {
			throw new RegraNegocioException("Informe o tipo do Medicamento");
		}

	}

	@Override
	public Optional<Medicamento> obterPorId(Long id) {
		return repository.findById(id);
	}

	@Override
	public void validarNomeMedicameto(String nome) {
		
		boolean exists = repository.existsByNome(nome);
		
		if (exists) {
			throw new RegraNegocioException("Já existe um medicamento cadastrado com este nome");
		}
	}

}
