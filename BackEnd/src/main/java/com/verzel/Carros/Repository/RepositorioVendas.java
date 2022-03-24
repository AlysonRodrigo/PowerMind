package com.verzel.Carros.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.verzel.Carros.Model.Venda;

public interface RepositorioVendas extends JpaRepository<Venda , Long>{

}
