package com.krt.helpdesk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krt.helpdesk.domain.dtos.TecnicoDTO;
import com.krt.helpdesk.domain.model.Pessoa;
import com.krt.helpdesk.domain.model.Tecnico;
import com.krt.helpdesk.repository.PessoaRepository;
import com.krt.helpdesk.repository.TecnicoRepository;
import com.krt.helpdesk.service.exception.DataIntegrityViolationException;
import com.krt.helpdesk.service.exception.ObjectNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id ));
	}


	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}


	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);
		validaCpfEEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO);
		return tecnicoRepository.save(newObj);
	}


	private void validaCpfEEmail(TecnicoDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema");
		}
	}

}
