package com.verzel.Carros.Controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verzel.Carros.Model.Venda;
import com.verzel.Carros.Repository.RepositorioVendas;

@RestController
@RequestMapping("/venda")
@CrossOrigin("*")
public class VendaController {
	
	private @Autowired RepositorioVendas repositorioVendas;
	
	
	@GetMapping("/todos")
	public ResponseEntity<List<Venda>> pegarTodos() {
		List<Venda> objetoLista = repositorioVendas.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@PostMapping("/salvar")
	public ResponseEntity<Object> salvar(@Valid @RequestBody Venda novaConta) {
		
		return ResponseEntity.status(201).body(repositorioVendas.save(novaConta));
	}
	
	@PostMapping("/calcular")
	public ResponseEntity<Object> calcular(@RequestBody Venda venda) {

		System.out.println("id carro" + venda.getValorTotal());
		System.out.println("qtde parcela" + venda.getQuantParcela() );
		
		venda.setValorParcela( venda.getValorTotal() / venda.getQuantParcela());    
		
		return ResponseEntity.status(201).body(venda);
	}
}
