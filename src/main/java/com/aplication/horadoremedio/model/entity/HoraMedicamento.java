package com.aplication.horadoremedio.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.format.annotation.DateTimeFormat;

import com.aplication.horadoremedio.model.enums.StatusMedicamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "horaMedicamento")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HoraMedicamento {
	
	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private StatusMedicamento status;
	
	@ManyToOne
	@JoinColumn(name = "id_medicamento")
	private Medicamento idMedicamento;

	@DateTimeFormat(pattern = " HH:mm:ss")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	@Column(name = "hora_medicamento")
	private LocalDate  horaMedicamento;
}
