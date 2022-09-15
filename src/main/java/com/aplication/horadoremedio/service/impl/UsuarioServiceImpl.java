package com.aplication.horadoremedio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aplication.horadoremedio.exception.RegraNegocioException;
import com.aplication.horadoremedio.model.entity.Usuario;
import com.aplication.horadoremedio.model.repository.UsuarioRepository;
import com.aplication.horadoremedio.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
		
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		// TODO Auto-generated method stub
		return null;
	}

	
	// antes de salvar usuário é preciso validar o email
	// passando o email do usuario para o método validar email
	// caso não exita email cadastrado salva o usuário na base de dados
	// retorna uma instância do usuario adastrado
	// abrir uma tranzação para salvar os dados na base e depois commitar os 
	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}
	
	// verificar se existe um usuário cadastrado com o email recebido por parâmetro
	// caso o email já esteja cadastrado dispara uma exceção com a mensagem email em uso.
	@Override
	public void validarEmail(String email) {
		
		boolean existe = repository.existsByEmail(email);
		
		if (existe) {
			throw new RegraNegocioException("Já existe usuário cadastrado com esta email");
		}
		
	}

}
