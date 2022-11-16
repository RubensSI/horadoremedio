package com.aplication.horadoremedio.api.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aplication.horadoremedio.api.dto.UsuarioDTO;
import com.aplication.horadoremedio.exception.ErroAutenticacao;
import com.aplication.horadoremedio.exception.RegraNegocioException;
import com.aplication.horadoremedio.model.entity.Usuario;
import com.aplication.horadoremedio.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;

	public UsuarioResource(UsuarioService service) {
		this.service = service;
	}

	// rota para autenticacao de usuario.
	// receber uma requisição para autentiacao de usario no formato json no metodo
	// autenticar.
	// retornar status ok caso usario exista no bonco, caso contrario retornar uma
	// Exception.
	// com uma mensagem de erro: usuario não cadastrado.

	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody UsuarioDTO dto) {

		try {

			Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
			return ResponseEntity.ok(usuarioAutenticado);

		} catch (ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	// rota para cadastro de um novo usuário
	// receber uma requição do tipo post em formato json contendo o os dados de
	// usuario
	// passar os dados para serem salvos no banco e retornar uma instâcia uma do
	// novo usuario

	@PostMapping
	public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {

		Usuario usuario = Usuario.builder()
				.nome(dto.getNome())
				.email(dto.getEmail())
				.telefone(dto.getTelefone())
				.senha(dto.getSenha()).build();

		try {
			Usuario usuarioSalvo = service.salvarUsuario(usuario);
			return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("{id}/usuario")
	public ResponseEntity obterUsuario(@PathVariable("id") Long id) {
		Optional<Usuario> usuario = service.obterPorId(id);

		if (!usuario.isPresent()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(usuario);
	}

}
