package com.aplication.horadoremedio;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.aplication.horadoremedio.model.entity.Usuario;
import com.aplication.horadoremedio.model.repository.UsuarioRepository;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class HoradoremedioApplicationTests {

	@Autowired
	UsuarioRepository repository;

	@Autowired
	EntityManager entityManager;

	@Test
	public void deveVerificarAExistenciadeUmEmail() {

		// cenario
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);

		// ação / execução

		boolean resultado = repository.existsByEmail("rubens@gmail.com");

		// verificação
		Assertions.assertThat(resultado).isTrue();

	}

	@Test
	public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {
		// cenario
		repository.deleteAll();

		// ação
		boolean resultado = repository.existsByEmail("usuario@gmail.com");

		// verifição
		Assertions.assertThat(resultado).isFalse();
	}

	@Test
	public void devePersistirUmUsuarioNaBaseDeDados() {

		// cenario
		Usuario usuario = criarUsuario();

		// ação
		Usuario usuarioSalvo = repository.save(usuario);

		// verificação
		Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
	}

	public void deveBuscarUmUsuarioPorEmail() {
		
		// cenario 
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		
		// verificacao
		Optional<Usuario> resultado = repository.findByEmail("rubens@gmail.com");
		
		Assertions.assertThat(resultado.isPresent() ).isTrue();
		
	}

	public static Usuario criarUsuario() {
		return Usuario
				.builder()
				.nome("usuario")
				.email("rubens@gmail.com")
				.telefone("12345678")
				.senha("12345#")
				.build();
	}

}
