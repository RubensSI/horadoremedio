package com.aplication.horadoremedio.service;

import com.aplication.horadoremedio.model.entity.Usuario;

public interface UsuarioService {
	
	// define os métodos para trabalhar com o usuário
	// o método autenticar da classe concreta que implementa a iterface UsuarioService
	// vei receber o email e senha vai na base de dados se existir, verificar se a senha corresponde
	// caso exista retorna uma instâcia de usuário autenticado.
	Usuario autenticar(String email, String senha);

	// o método vai salvar usuario na base dado e retornar uma instancia de usuario
	// caso ele ainda não esteja na base de dados. caso contrario retorna uma mensagem de erro.
	Usuario salvarUsuario(Usuario usuario);

	// verificar se email está salvo na base de dados
	// caso estaja retone uma menssaegem de erro
	void validarEmail(String email);
}
