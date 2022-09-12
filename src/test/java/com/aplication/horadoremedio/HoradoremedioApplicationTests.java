package com.aplication.horadoremedio;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.aplication.horadoremedio.model.entity.Usuario;
import com.aplication.horadoremedio.model.repository.UsuarioRepository;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class HoradoremedioApplicationTests {
	
	@Autowired
	UsuarioRepository repository;

	@Test
	void contextLoads() {
		
		// cenario
		Usuario usuario = Usuario.builder().nome("usuario").email("usuario@gmail.com").build();
		repository.save(usuario);
				
		// ação / execução
				
		boolean resultado = repository.existsByEmail("usuario@gmail.com");
				
		// verificação
		Assertions.assertThat(resultado).isTrue();
	
	}
	
	@Test
	void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {
		// cenario
		repository.deleteAll();
		
		// ação 
		boolean resultado = repository.existsByEmail("usuario@gmail.com");
		
		// verifição
		Assertions.assertThat(resultado).isFalse();
	}

}
