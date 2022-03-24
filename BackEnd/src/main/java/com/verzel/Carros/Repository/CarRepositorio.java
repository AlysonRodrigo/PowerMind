package com.verzel.Carros.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.verzel.Carros.Model.Car;

public interface CarRepositorio extends JpaRepository<Car, Long>{
	List<Car> findAllByMarcaContainingIgnoreCase(String marca);
}
