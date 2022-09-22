package com.aplication.horadoremedio.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioDTO {
	
	private String nome;
	private String email;
	private String telefone;
	private String senha;

}
