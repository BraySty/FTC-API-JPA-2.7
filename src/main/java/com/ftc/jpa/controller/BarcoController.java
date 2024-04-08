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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BarcoController {

    private final BarcoService barcoService;

    @GetMapping("/barcos/{matricula}")
    public ResponseEntity<?> findBarco(@PathVariable("matricula") String matricula) {
        return barcoService.findById(matricula);
	}

    @PostMapping("/barcos")
    @Operation(summary = "Crea un Barco.", description = "Crea un barco con o sin relacion.")
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
	public ResponseEntity<String> createBarco(@RequestBody Barco barco) {
        return barcoService.create(barco);
	}

    @PutMapping("/barcos")
    public ResponseEntity<String> updateBarco(@RequestBody Barco barco) {
        return barcoService.updateById(barco);
	}

    @DeleteMapping("/barcos/{matricula}")
    public ResponseEntity<String> deleteBarco(@PathVariable("matricula") String matricula) {
        return barcoService.deleteById(matricula);
	}

}
