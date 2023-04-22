package com.krt.helpdesk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.krt.helpdesk.domain.enums.Perfil;
import com.krt.helpdesk.domain.enums.Prioridade;
import com.krt.helpdesk.domain.enums.Status;
import com.krt.helpdesk.domain.model.Chamado;
import com.krt.helpdesk.domain.model.Cliente;
import com.krt.helpdesk.domain.model.Tecnico;
import com.krt.helpdesk.repository.ChamadoRepository;
import com.krt.helpdesk.repository.ClienteRepository;
import com.krt.helpdesk.repository.TecnicoRepository;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Tecnico tec1 = new Tecnico(null, "Karte Nascimento", "39008551616", "karte@gmail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Jucelino Alcantara", "21335696423", "Jucelino@gmail.com", "123");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		chamadoRepository.saveAll(Arrays.asList(c1));
	}

}
