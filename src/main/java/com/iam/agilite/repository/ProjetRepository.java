package com.iam.agilite.repository;

import org.springframework.data.repository.CrudRepository;

import com.iam.agilite.entity.Projet;

public interface ProjetRepository extends CrudRepository<Projet, Long> {

	Projet findByCode(String code);
	boolean existsByCode(String code);
}
