package com.aplication.horadoremedio.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.aplication.horadoremedio.model.entity.Usuario;
import com.aplication.horadoremedio.model.repository.UsuarioRepository;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class UsuarioServiceTest {
	
	@Autowired
	UsuarioService service;
	
	@Autowired
	UsuarioRepository repository;
	
	@Test
	public void deveValidarEmail() {
		// cenario
		
		// instância mockada
		// vai fazer os testes unitários sem fazer a chamada ao banco de dados.
		// simula como se estivesse buscando o usuário no banco
		UsuarioRepository usuarioRepositoryMock = Mockito.mock(UsuarioRepository.class);
		
		repository.deleteAll();
		
		// ação
		service.validarEmail("email@gmail.com");
	}
	
	@Test
	public void deveLancarErroAoValidarQuandoExistirEmailCadastrado() {
		// senario
		Usuario usuario = Usuario.builder().nome("Usuario").email("email2@gmail.com").build();
		repository.save(usuario);
		
		// ação
		service.validarEmail("email@gmail.com");
	}

}
