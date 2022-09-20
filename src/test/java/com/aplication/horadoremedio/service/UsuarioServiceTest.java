package com.aplication.horadoremedio.service;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.transaction.BeforeTransaction;

import com.aplication.horadoremedio.model.entity.Usuario;
import com.aplication.horadoremedio.model.repository.UsuarioRepository;
import com.aplication.horadoremedio.service.impl.UsuarioServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class UsuarioServiceTest {
	
	UsuarioService service;
	UsuarioRepository repository;
	
	@BeforeEach
	public void setUp() {
		repository = Mockito.mock(UsuarioRepository.class);
		service = new UsuarioServiceImpl(repository);
	}
	
	@Test
	public void deveValidarEmail() {
		// cenario
		
		// instância mockada
		// vai fazer os testes unitários sem fazer a chamada ao banco de dados.
		// simula como se estivesse buscando o usuário no banco
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
	
		
		// ação
		service.validarEmail("email@gmail.com");
	}
	
	@Test
	public void deveLancarErroAoValidarQuandoExistirEmailCadastrado() {
		// senario
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);
		
		// ação
		service.validarEmail("email@gmail.com");
	}

}
