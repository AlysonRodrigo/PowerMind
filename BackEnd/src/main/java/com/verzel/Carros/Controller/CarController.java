package com.verzel.Carros.Controller;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.verzel.Carros.Model.Car;
import com.verzel.Carros.Repository.CarRepositorio;
@RestController
@RequestMapping("/carros")
@CrossOrigin("*")
public class CarController {
	private @Autowired CarRepositorio repositorio;
	
	@GetMapping("/todos")
	public ResponseEntity<List<Car>> pegarTodos() {
		List<Car> objetoLista = repositorio.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@PostMapping("/salvar")
	public ResponseEntity<Object> salvar(@Valid @RequestBody Car novoCarro) {

		return ResponseEntity.status(201).body(repositorio.save(novoCarro));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Car> GetById(@PathVariable long idCar){
		return repositorio.findById(idCar)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/marca/{marca}")
	public ResponseEntity<List<Car>> buscarPorMarca(@PathVariable(value = "marca") String marca) {
		List<Car> objetoLista = repositorio.findAllByMarcaContainingIgnoreCase(marca);

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@GetMapping("/pesquisa")
	public ResponseEntity<List<Car>> buscarPorMarcaII(@RequestParam(defaultValue = "") String marca) {
		List<Car> objetoLista = repositorio.findAllByMarcaContainingIgnoreCase(marca);

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Car> atualizar(@Valid @RequestBody Car marcaParaAtualizar) {
		return ResponseEntity.status(200).body(repositorio.save(marcaParaAtualizar));
	}

	@DeleteMapping("/{idCar}")
	public void deletarCarPorId(@PathVariable(value = "idCar") Long idCar) {
		repositorio.deleteById(idCar);
	}
}
