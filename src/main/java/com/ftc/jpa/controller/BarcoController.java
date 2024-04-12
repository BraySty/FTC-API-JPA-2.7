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

import com.ftc.jpa.entitys.Barco;
import com.ftc.jpa.service.BarcoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BarcoController {

    private final BarcoService barcoService;

    @PostMapping("/barcos")
	public ResponseEntity<String> createBarco(@RequestBody Barco barco) {
        return barcoService.create(barco);
	}

    @PostMapping("/barcos/{dni}")
	public ResponseEntity<String> createBarcoWithSocio(@RequestBody Barco barco, @PathVariable("dni") String dni) {
        return barcoService.create(barco, dni);
	}

    @GetMapping("/barcos")
    public ResponseEntity<?> findBarco() {
        return barcoService.findAll();
	}

    @GetMapping("/barcos/{matricula}")
    public ResponseEntity<?> findBarco(@PathVariable("matricula") String matricula) {
        return barcoService.findById(matricula);
	}

    @PutMapping("/barcos")
    public ResponseEntity<String> updateBarco(@RequestBody Barco barco) {
        return barcoService.updateById(barco);
	}

    @PutMapping("/barcos/{matricula}/{dni}")
	public ResponseEntity<?> createBarcoWithSocio(@PathVariable("matricula") String matricula, @PathVariable("dni") String dni) {
        return barcoService.update(matricula, dni);
	}

    @DeleteMapping("/barcos/{matricula}")
    public ResponseEntity<String> deleteBarco(@PathVariable("matricula") String matricula) {
        return barcoService.deleteById(matricula);
	}

}
