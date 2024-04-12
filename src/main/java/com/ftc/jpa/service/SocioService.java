package com.ftc.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ftc.jpa.entitys.Socio;
import com.ftc.jpa.repository.SocioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SocioService {

    @Autowired
    private SocioRepository socioRepository;
    private String internalError = "Se ha producido un error.\n";
    private String notFound = "No se encontro el socio con id: ";

    public ResponseEntity<String> create(Socio socio) {
        String dni = socio.getDni();
        Optional<Socio> busqueda = socioRepository.findById(dni);
        try {
            if (busqueda.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un socio con el DNI: " + dni);
            } else {
                socioRepository.save(socio);
                return ResponseEntity.status(HttpStatus.CREATED).body("Se ha creado el socio con el DNI: " + dni);
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError + e.getLocalizedMessage());
        }
    }

    public ResponseEntity<?> findAll() {
        List<Socio> busqueda = socioRepository.findAll();
        try {
            if (!busqueda.isEmpty()) {
                return ResponseEntity.status(HttpStatus.FOUND).body(busqueda);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay ningun socio.");
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError + e.getLocalizedMessage());
        }
    }

    public ResponseEntity<?> findById(String dni) {
        try {
            Optional<Socio> busqueda = socioRepository.findById(dni);
            if (busqueda.isPresent()) {
                return ResponseEntity.status(HttpStatus.FOUND).body(busqueda.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound + dni);
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError + e.getLocalizedMessage());
        }
    }

    public ResponseEntity<String> updateById(Socio socio) {
        String dni = socio.getDni();
        Optional<Socio> update = socioRepository.findById(dni);
        try {
            if (update.isPresent()) {
                update.get().setNombre(socio.getNombre());
                update.get().setTelefono(socio.getTelefono());
                update.get().setEmail(socio.getEmail());
                update.get().setDireccion(socio.getDireccion());
                update.get().setBarcos(socio.getBarcos());
                socioRepository.save(update.get());
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Se cambiaron los datos del socio con el DNI: " + dni);
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body(notFound + dni);
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError + e.getLocalizedMessage());
        }
    }

    public ResponseEntity<String> deleteById(String dni) {
        try {
            Optional<Socio> barco = socioRepository.findById(dni);
            if (barco.isPresent()) {
                socioRepository.deleteById(dni);
                return ResponseEntity.status(HttpStatus.OK).body("Se ha eliminado el socio con el DNI: " + dni);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound + dni);
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError + e.getMessage());
        }
    }

}
