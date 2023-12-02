package com.empresa.entregadores.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.empresa.entregadores.model.EntregadorModel;

@Repository
public interface EntregadorRepository extends JpaRepository<EntregadorModel, Integer>{

}