package com.aplication.horadoremedio.api.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aplication.horadoremedio.api.dto.MedicamentoDTO;
import com.aplication.horadoremedio.service.MedicamentoService;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoResource {
	
	private MedicamentoService service;

	public MedicamentoResource(MedicamentoService service) {
		super();
		this.service = service;
	}
	
//	@PostMapping
//	public ResponseEntity salvar( @RequestBody MedicamentoDTO dto) {
//		
//	}
	
	
}
