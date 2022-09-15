package com.aplication.horadoremedio.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aplication.horadoremedio.api.dto.UsuarioDto;
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

	@PostMapping
	public ResponseEntity salvar( @RequestBody UsuarioDto dto ) {
		
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
	
}
