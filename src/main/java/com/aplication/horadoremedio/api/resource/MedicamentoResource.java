package com.aplication.horadoremedio.api.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aplication.horadoremedio.api.dto.AtualizaStatusDTO;
import com.aplication.horadoremedio.api.dto.MedicamentoDTO;
import com.aplication.horadoremedio.exception.RegraNegocioException;
import com.aplication.horadoremedio.model.entity.Medicamento;
import com.aplication.horadoremedio.model.entity.Usuario;
import com.aplication.horadoremedio.model.enums.StatusMedicamento;
import com.aplication.horadoremedio.model.enums.TipoUsoMedicamento;
import com.aplication.horadoremedio.service.MedicamentoService;
import com.aplication.horadoremedio.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/medicamentos")
@RequiredArgsConstructor
public class MedicamentoResource {

	private final MedicamentoService service;

	private final UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity buscar(@RequestParam(value = "descricao", required = false) String descricao,
			@RequestParam(value = "nome", required = false) String nome, @RequestParam("usuario") Long idUsuario) {
		Medicamento medicamentofiltro = new Medicamento();
		medicamentofiltro.setNome(nome);
		medicamentofiltro.setDescricao(descricao);

		Optional<Usuario> usuario = usuarioService.obterPorId(idUsuario);
		if (!usuario.isPresent()) {
			return ResponseEntity.badRequest()
					.body("Não foi possível realizar a consulta. Usuario não encontrado para o id informado.");
		} else {
			medicamentofiltro.setUsuario(usuario.get());
		}

		List<Medicamento> medicametos = service.buscar(medicamentofiltro);
		return ResponseEntity.ok(medicametos);
	}

	@PostMapping
	public ResponseEntity salvar(@RequestBody MedicamentoDTO dto) {
		try {
			Medicamento entidade = converter(dto);
			entidade = service.salvar(entidade);
			return new ResponseEntity(entidade, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody MedicamentoDTO dto) {
		return service.obterPorId(id).map(entity -> {
			try {

				Medicamento medicamento = converter(dto);
				medicamento.setId(entity.getId());
				service.atualizar(medicamento);
				return ResponseEntity.ok(medicamento);
			} catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet(() -> new ResponseEntity("Medicamento não encontrado na base de dados.", HttpStatus.BAD_REQUEST));
	}

	@PutMapping("{id}/atualizar-status")
	public ResponseEntity atualizarStatus(@PathVariable("id") Long id, @RequestBody AtualizaStatusDTO dto) {
		return service.obterPorId(id).map(entity -> {
			StatusMedicamento statusSelecionado = StatusMedicamento.valueOf(dto.getStatus());

			if (statusSelecionado == null) {
				return ResponseEntity.badRequest()
						.body("Não foi possível atualizar o status do madicamento, envie um status válido.");
			}

			try {
				entity.setStatus(statusSelecionado);
				service.atualizar(entity);
				return ResponseEntity.ok(entity);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet(() -> new ResponseEntity("Medicamento não encontrado na base de dados.", HttpStatus.BAD_REQUEST));
	}

	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id") Long id) {
		return service.obterPorId(id).map(entidade -> {
			service.deletar(entidade);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}).orElseGet(() -> new ResponseEntity("Medicamento não encontrado na base de dados.", HttpStatus.BAD_REQUEST));
	}

	private Medicamento converter(MedicamentoDTO dto) {

		Medicamento medicamento = new Medicamento();

		medicamento.setNome(dto.getNome());
		medicamento.setId(dto.getId());
		medicamento.setDescricao(dto.getDescricao());
		medicamento.setDataCadastro(dto.getDataCadastro());

		Usuario usuario = usuarioService.obterPorId(dto.getUsuario())
				.orElseThrow(() -> new RegraNegocioException("Usuario não encontrado para o id informado."));

		medicamento.setUsuario(usuario);

		if (dto.getTipo() != null) {
			medicamento.setTipo(TipoUsoMedicamento.valueOf(dto.getTipo()));
		}

		if (dto.getStatus() != null) {
			medicamento.setStatus(StatusMedicamento.valueOf(dto.getStatus()));
		}

		return medicamento;
	}

}
