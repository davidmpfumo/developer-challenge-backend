package mz.gov.backenddeveloperchallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mz.gov.backenddeveloperchallenge.model.Pais;

public interface PaisRepository extends JpaRepository<Pais, Long> {
	Pais findByNome(String nome);
}