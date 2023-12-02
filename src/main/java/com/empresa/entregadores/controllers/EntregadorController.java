package com.empresa.entregadores.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entregadores.repositories.*;

import jakarta.validation.Valid;

import com.empresa.entregadores.model.*;
import com.empresa.entregadores.dto.*;

@RestController
public class EntregadorController {

	@Autowired
	EntregadorRepository entregadorRepository;
	
	@GetMapping("/entregadores")
	public ResponseEntity<List<EntregadorModel>> Get() {
		List<EntregadorModel> entregadorList = entregadorRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(entregadorList);
	}
	
	@GetMapping("/entregadores/{id}")
	public ResponseEntity<Object> GetById(@PathVariable(value="id") Integer id) {
		Optional<EntregadorModel> entregadorById = entregadorRepository.findById(id);
		if(entregadorById.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entregador não foi encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(entregadorById.get());
	}
	
	@PostMapping("/create-entregadores")
	public ResponseEntity<EntregadorModel> Post(@RequestBody @Valid EntregadorDto entregadorDto) {
		var entregadorModel = new EntregadorModel();
		BeanUtils.copyProperties(entregadorDto, entregadorModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(entregadorRepository.save(entregadorModel));
	}
	
	@PutMapping("/entregadores/{id}")
	public ResponseEntity<Object> updateEntregador(@PathVariable(value="id") Integer id, @RequestBody @Valid EntregadorDto entregadorDto) {
		Optional<EntregadorModel> entregador = entregadorRepository.findById(id);
		if(entregador.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado para edição.");
		}
		var entregadorModel = entregador.get();
		BeanUtils.copyProperties(entregadorDto, entregadorModel);
		return ResponseEntity.status(HttpStatus.OK).body(entregadorRepository.save(entregadorModel));
	}
	
	@DeleteMapping("/entregadores/{id}")
	public ResponseEntity<Object> deleteEntregador(@PathVariable(value="id") Integer id) {
		Optional<EntregadorModel> filme = entregadorRepository.findById(id);
		if(filme.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entregador não encontrado para exclusão.");
		}
		entregadorRepository.delete(filme.get());
		return ResponseEntity.status(HttpStatus.OK).body("O Entregador foi excluído.");
	}
}
