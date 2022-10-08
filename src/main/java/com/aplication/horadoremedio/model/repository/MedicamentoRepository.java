package com.aplication.horadoremedio.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aplication.horadoremedio.model.entity.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
		
}
