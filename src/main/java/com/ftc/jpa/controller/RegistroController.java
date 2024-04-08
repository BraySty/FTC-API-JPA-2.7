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
    @Operation(summary = "Crea un registro.", description = "Crea un registro con o sin relacion a Patron.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "CREATED", 
        content = {
            @Content(mediaType = "String", schema = @Schema(implementation = String.class))
        }),
        @ApiResponse(responseCode = "409", description = "CONFLICT", 
        content = {
            @Content(mediaType = "String", schema = @Schema(implementation = String.class))
        }),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR", 
        content = {
            @Content(mediaType = "String", schema = @Schema(implementation = String.class))
        })})
	public ResponseEntity<String> createBarco(@RequestBody Registro registro) {
        return registroService.create(registro);
	}

    @GetMapping("/registros/{id}")
    public ResponseEntity<?> findBarco(@PathVariable("id") int id) {
        return registroService.findById(id);
	}

    @PutMapping("/registros")
    public ResponseEntity<String> updateBarco(@RequestBody Registro registro) {
        return registroService.updateById(registro);
	}

    @DeleteMapping("/registros/{id}")
    public ResponseEntity<String> deleteBarco(@PathVariable("id") int id) {
        return registroService.deleteById(id);
	}

}
