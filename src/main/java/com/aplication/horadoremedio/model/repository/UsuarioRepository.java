package com.aplication.horadoremedio.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aplication.horadoremedio.model.entity.Usuario;

//a interface prove os métodos necessários para trbalhos com bancos de dados
//com relaçção a classe usuario, ela recebe o objeto Usuario e tipo do campo id desse
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	// O tipo Optional vai retornar uma instância do usuario caso esteje cadastrado
	// vazio coso não esteja.
	// Optional<Usuario> findByEmail(String email);
		
	// verica apenas se existe o usuario com o email recebido.
	boolean existsByEmail(String email);
}
