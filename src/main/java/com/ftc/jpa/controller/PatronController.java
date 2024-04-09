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
	public ResponseEntity<String> createBarco(@RequestBody Patron patron) {
        return patronService.create(patron);
	}

    @GetMapping("/patrones")
    public ResponseEntity<?> findBarco() {
        return patronService.findAll();
	}

    @GetMapping("/patrones/{dni}")
    public ResponseEntity<?> findBarco(@PathVariable("dni") String dni) {
        return patronService.findById(dni);
	}

    @PutMapping("/patrones")
    public ResponseEntity<String> updateBarco(@RequestBody Patron patron) {
        return patronService.updateById(patron);
	}

    @DeleteMapping("/patrones/{dni}")
    public ResponseEntity<String> deleteBarco(@PathVariable("dni") String dni) {
        return patronService.deleteById(dni);
	}

}
