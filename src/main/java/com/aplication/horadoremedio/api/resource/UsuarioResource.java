package com.aplication.horadoremedio.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aplication.horadoremedio.api.dto.UsuarioDto;
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
	
	// metodo para autenticão de usuario que recebe os dados no formato json via requisicão
	// metodo post, que são passados para o metodo autenticar que vai verificar se o usuario 
	// já está persistido no banco caso não esteja retorna uma excessão informando que usuario
	// não está cadastrado no banco
	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody UsuarioDto dto) {
		
		try {
			 Usuario usuarioAutenticdo = service.autenticar(dto.getEmail(), dto.getSenha());
			// retornar uma instância de usuario auttenticado caso exista, com o código 204
			return ResponseEntity.ok(usuarioAutenticdo);
			
		} catch (ErroAutenticacao e) {
			
			// retornar uma exception erroAutenticacao para informar que usuario não foi cadastrado
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	// metodo para salvar usuario: recebe uma requisicao no formato json com os dadas do
	// usuario que são passados como parametro para a metodo salvar que depois esses dados
	// são usados para ser persistidos no banco com um novo usuario caso não exista no banco
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
