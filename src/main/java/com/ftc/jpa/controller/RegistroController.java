package com.ftc.jpa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftc.jpa.entitys.Registro;
import com.ftc.jpa.service.RegistroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RegistroController {

    private final RegistroService registroService;

    @PostMapping("/registros")
	public ResponseEntity<String> createRegistro(@RequestBody Registro registro) {
        return registroService.create(registro);
	}

    @GetMapping("/registros")
    public ResponseEntity<?> findRegistro() {
        return registroService.findAll();
	}

    @GetMapping("/registros/{id}")
    public ResponseEntity<?> findRegistro(@PathVariable("id") int id) {
        return registroService.findById(id);
	}

    @PutMapping("/registros")
    public ResponseEntity<String> updateRegistro(@RequestBody Registro registro) {
        return registroService.updateById(registro);
	}
    
    @PutMapping("/registros/patron/{id}/{dni}")
    public ResponseEntity<String> updateRegistroWithBarco(@PathVariable("id") int id, @PathVariable("dni") String dni) {
        return registroService.updateRegistroWithPatron(id, dni);
	}

    @PutMapping("/registros/barco/{id}/{matricula}")
    public ResponseEntity<String> updateRegistroWithPatron(@PathVariable("id") int id, @PathVariable("matricula") String matricula) {
        return registroService.updateRegistroWithBarco(id, matricula);
	}

    @DeleteMapping("/registros/{id}")
    public ResponseEntity<String> deleteRegistro(@PathVariable("id") int id) {
        return registroService.deleteById(id);
	}

}
