package com.aplication.horadoremedio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

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

	
	// antes salvar usuário é preciso validar o email
	// passando o email do usuario para o metodo validar email
	@Override
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		
		boolean existe = repository.existsByEmail(email);
		
		if (existe) {
			throw new RegraNegocioException("Já existe usuário cadastrado com esta email");
		}
		
	}

}
