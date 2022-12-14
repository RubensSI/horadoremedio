package com.aplication.horadoremedio.model.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hora_medicamento")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HoraMedicamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "data_hora")
	@Convert(converter = Jsr310JpaConverters.LocalTimeConverter.class)
	private Date dataHora;

	@ManyToOne
	@JoinColumn(name = "id_medicamento")
	private Medicamento medicamento;

}
