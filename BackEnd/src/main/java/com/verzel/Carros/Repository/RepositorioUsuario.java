package com.verzel.Carros.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verzel.Carros.Model.Usuario;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {
	List<Usuario> findAllByNomeContainingIgnoreCase(String nome);

	Optional<Usuario> findByEmail(String email);
}
