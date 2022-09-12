package com.aplication.horadoremedio.model.repository;

import org.springframework.beans.factory.annotation.Autowired;

import com.aplication.horadoremedio.model.entity.Usuario;

import lombok.experimental.ExtensionMethod;

@ExtendWith( SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {
	
	@Autowired
	UsuarioRepository repository;
	
	@Test
	public void deveVerificarAExistenciaDeUmEmail() {
		// cenario
		Usuario usuario = Usuario.builder().name("usuario").email("usuario@gmail.com").build();
		
		// ação / execução
		
		boolean resultado = repository.existsByEmail("usuario@gmail.com");
		
		// verificação
		Assertions.assertThat(resultado).isTrue();
		
		
	}
	
}
