package com.verzel.Carros.Controller;

import java.util.List;

import java.util.Optional;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.verzel.Carros.Model.Usuario;
import com.verzel.Carros.Model.Utilidade.UsuarioDTO;
import com.verzel.Carros.Repository.RepositorioUsuario;
import com.verzel.Carros.Servicos.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

	private @Autowired RepositorioUsuario repositorio;
	private @Autowired UsuarioService usuarioService;
	
	@GetMapping("/todos")
	public ResponseEntity<List<Usuario>> pegarTodos() {
		List<Usuario> objetoLista = repositorio.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@PostMapping("/salvar")
	public ResponseEntity<Object> salvarUsuario(@Valid @RequestBody Usuario NovoUsuario) {
		Optional<Object> objectOptional = usuarioService.salvarUsuario(NovoUsuario);

		if (objectOptional.isEmpty()) {
			return ResponseEntity.status(400).build();
		} else {
			return ResponseEntity.status(201).body(objectOptional.get());
		}
	}

	@PutMapping("/credenciais")
	public ResponseEntity<Object> credenciais(@Valid @RequestBody UsuarioDTO usuarioParaAutenticar){
		Optional<?> objetoOptional = usuarioService.pegarCredenciais(usuarioParaAutenticar);
		if (objetoOptional.isEmpty()) {
			return ResponseEntity.status(400).build();
		}else {
			return ResponseEntity.status(201).body(objetoOptional.get());
		}
	}

	@GetMapping("/{idUsuario}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable(value = "idUsuario") Long idUsuario) {
		Optional<Usuario> objetoUsuario = repositorio.findById(idUsuario);

		if (objetoUsuario.isPresent()) {
			return ResponseEntity.status(200).body(objetoUsuario.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Usuario>> buscarPorNomeI(@PathVariable(value = "nome") String nome) {
		List<Usuario> objetoLista = repositorio.findAllByNomeContainingIgnoreCase(nome);

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@GetMapping("/pesquisa")
	public ResponseEntity<List<Usuario>> buscarPorNomeII(@RequestParam(defaultValue = "") String nome) {
		List<Usuario> objetoLista = repositorio.findAllByNomeContainingIgnoreCase(nome);

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> atualizar(@Valid @RequestBody Usuario usuarioParaAtualizar) {
		return ResponseEntity.status(201).body(repositorio.save(usuarioParaAtualizar));
	}

	@DeleteMapping("/deletar/{idUsuario}")
	public void deletarUsuarioPorId(@PathVariable(value = "idUsuario") Long idUsuario) {
		repositorio.deleteById(idUsuario);
	}

}
