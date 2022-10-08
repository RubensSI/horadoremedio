package com.aplication.horadoremedio.api.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aplication.horadoremedio.api.dto.HoraMedicamentoDTO;
import com.aplication.horadoremedio.exception.RegraNegocioException;
import com.aplication.horadoremedio.model.entity.HoraMedicamento;
import com.aplication.horadoremedio.model.entity.Medicamento;
import com.aplication.horadoremedio.service.HoraMedicamentoService;
import com.aplication.horadoremedio.service.MedicamentoService;
import com.aplication.horadoremedio.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/hora")
@RequiredArgsConstructor
public class HoraMedicamentoResource {
	
	private final HoraMedicamentoService service;
	private final MedicamentoService medicamentoService;
	
	// método para salvar um novo horario de madicamento
	@PostMapping
	public ResponseEntity salvar( @RequestBody HoraMedicamentoDTO dto) {
		try {
			HoraMedicamento entidade = converter(dto);
			System.out.println(entidade);
			entidade = service.salvar(entidade);
			return new ResponseEntity(entidade, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	// criar uma método para converter um objeto dto em um objeto HoraMadicacao
	public HoraMedicamento converter(HoraMedicamentoDTO dto) {
		
		// instanciar um objeto horaMedicamento
		HoraMedicamento horaMedicamento = new HoraMedicamento();
		horaMedicamento.setId(dto.getId());
		horaMedicamento.setDescricao(dto.getDescricao());
		horaMedicamento.setDataHora(dto.getHora());
		
		// fazer verificação de medicamento
		Medicamento medicamento = medicamentoService.obterPorId(dto.getMedicamento())
				.orElseThrow(() -> new RegraNegocioException("Medicamento não encontrado para o id informado."));
		
		horaMedicamento.setMedicamento(medicamento);
		
		return horaMedicamento;

	}

}
