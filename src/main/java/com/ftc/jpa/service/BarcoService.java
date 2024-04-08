package com.ftc.jpa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ftc.jpa.entitys.Barco;
import com.ftc.jpa.repository.BarcoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BarcoService {

    @Autowired
    private BarcoRepository barcoRepository;

    private String internalError = "Se ha producido un error.";
    private String notFound = "No se encontro el barco con matricula: ";

    public ResponseEntity<String> create(Barco barco) {
        String matricula = barco.getMatricula();
        Optional<Barco> busqueda = barcoRepository.findById(matricula);
        try {
            if (busqueda.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un barco con matriula: " + matricula);
            } else {
                barcoRepository.save(barco);
                return ResponseEntity.status(HttpStatus.CREATED).body("Se ha creado el barco.");
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError);
        }
    }

    public ResponseEntity<?> findById(String matricula) {
        try {
            Optional<Barco> barco = barcoRepository.findById(matricula);
            if (barco.isPresent()) {
                return ResponseEntity.status(HttpStatus.FOUND).body(barco.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound + matricula);
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError);
        }
    }

    public ResponseEntity<String> updateById(Barco barco) {
        String matricula = barco.getMatricula();
        Optional<Barco> update = barcoRepository.findById(matricula);
        try {
            if (update.isPresent()) {
                update.get().setNombre(barco.getNombre());
                update.get().setAmarre(barco.getAmarre());
                update.get().setCuota(barco.getCuota());
                update.get().setPatron(barco.getPatron());
                barcoRepository.save(update.get());
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Se cambiaron los datos del barco con matricula: " + matricula);
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body(notFound + matricula);
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError);
        }
    }

    public ResponseEntity<String> deleteById(String matricula) {
        try {
            Optional<Barco> barco = barcoRepository.findById(matricula);
            if (barco.isPresent()) {
                barcoRepository.deleteById(matricula);
                return ResponseEntity.status(HttpStatus.OK).body("Se ha eliminado el barco con la ID: " + matricula);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound + matricula);
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError);
        }
    }

}
