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

import com.ftc.jpa.entitys.Socio;
import com.ftc.jpa.service.SocioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SocioController {

    private final SocioService socioService;

    @PostMapping("/socios")
    public ResponseEntity<String> createSocio(@RequestBody Socio socio) {
        return socioService.create(socio);
	}

    @GetMapping("/socios")
    public ResponseEntity<?> findSocio() {
        return socioService.findAll();
	}

    @GetMapping("/socios/{dni}")
    public ResponseEntity<?> findSocio(@PathVariable("dni") String id) {
        return socioService.findById(id);
	}

    @PutMapping("/socios")
    public ResponseEntity<String> updateSocio(@RequestBody Socio socio) {
        return socioService.updateById(socio);
	}

    @DeleteMapping("/socios/{dni}")
    public ResponseEntity<String> deleteSocio(@PathVariable("dni") String id) {
        return socioService.deleteById(id);
	}

}
