package com.verzel.Carros.Servicos;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.verzel.Carros.Repository.RepositorioUsuario;
import com.verzel.Carros.Model.Usuario;
import com.verzel.Carros.Model.Utilidade.UsuarioDTO;

@Service
public class UsuarioService {
	@Autowired
	private RepositorioUsuario repository;

	public Optional<Object> salvarUsuario(Usuario user) {
		return repository.findByEmail(user.getEmail()).map(emailExists -> {
			return Optional.empty();
		}).orElseGet(() -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String passwordEncoder = encoder.encode(user.getSenha());
			user.setSenha(passwordEncoder);
			return Optional.ofNullable(repository.save(user));
		});
	}

	public Optional<?> pegarCredenciais(UsuarioDTO usuarioParaAutenticar){
		return repository.findByEmail(usuarioParaAutenticar.getEmail()).map(usuarioExistente -> {
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if (encoder.matches(usuarioParaAutenticar.getSenha(), usuarioExistente.getSenha())) {
				
				String estruturaBasic = usuarioParaAutenticar.getEmail() + ":" + usuarioParaAutenticar.getSenha(); // gustavoboaz@gmail.com:134652
				byte[] autorizacaoBase64 = Base64.encodeBase64(estruturaBasic.getBytes(Charset.forName("US-ASCII"))); // hHJyigo-o+i7%0ÍUG465sas=-
				String autorizacaoHeader = "Basic " + new String(autorizacaoBase64); // Basic hHJyigo-o+i7%0ÍUG465sas=-

				usuarioParaAutenticar.setToken(autorizacaoHeader);
				usuarioParaAutenticar.setId(usuarioExistente.getIdUsuario());
				usuarioParaAutenticar.setNome(usuarioExistente.getNome());
				usuarioParaAutenticar.setSenha(usuarioExistente.getSenha());
				usuarioParaAutenticar.setTipo(usuarioExistente.getTipo());
				return Optional.ofNullable(usuarioParaAutenticar); // Usuario Credenciado
			} else {
				return Optional.empty();
			}
			
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}
	
	public Optional<?> update(Usuario user) {

		return repository.findById(user.getIdUsuario()).map(userExists -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String passwordEncoder = encoder.encode(user.getSenha());

			userExists.setNome(user.getNome());
			userExists.setEmail(user.getEmail());
			userExists.setSenha(passwordEncoder);

			return Optional.ofNullable(repository.save(userExists));
		}).orElseGet(() -> {
			return Optional.empty();
		});
		
	}
}
