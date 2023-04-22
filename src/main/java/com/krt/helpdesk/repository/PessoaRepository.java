package com.krt.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krt.helpdesk.domain.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
