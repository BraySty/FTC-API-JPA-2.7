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

import com.ftc.jpa.entitys.Patron;
import com.ftc.jpa.service.PatronService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PatronController {

    private final PatronService patronService;

    @PostMapping("/patrones")
	public ResponseEntity<String> create(@RequestBody Patron patron) {
        return patronService.create(patron);
	}

    @PostMapping("/patrones/{matricula}")
	public ResponseEntity<String> createBarcoWithSocio(@RequestBody Patron patron, @PathVariable("matricula") String matricula) {
        return patronService.create(patron, matricula);
	}

    @GetMapping("/patrones")
    public ResponseEntity<?> find() {
        return patronService.findAll();
	}

    @GetMapping("/patrones/{dni}")
    public ResponseEntity<?> find(@PathVariable("dni") String dni) {
        return patronService.findById(dni);
	}

    @PutMapping("/patrones")
    public ResponseEntity<String> update(@RequestBody Patron patron) {
        return patronService.updateById(patron);
	}

    @PutMapping("/patrones/{dni}/{matricula}")
    public ResponseEntity<String> update(@PathVariable("dni") String dni, @PathVariable("matricula") String matricula) {
        return patronService.updateWithBarco(dni, matricula);
	}

    @DeleteMapping("/patrones/{dni}")
    public ResponseEntity<String> delete(@PathVariable("dni") String dni) {
        return patronService.deleteById(dni);
	}

}
