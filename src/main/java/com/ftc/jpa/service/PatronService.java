package com.ftc.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ftc.jpa.entitys.Barco;
import com.ftc.jpa.entitys.Patron;
import com.ftc.jpa.repository.BarcoRepository;
import com.ftc.jpa.repository.PatronRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatronService {

    @Autowired
    private PatronRepository patronRepository;
    @Autowired
    private BarcoRepository barcoRepository;
    
    private String internalError = "Se ha producido un error.\n";
    private String notFound = "No se encontro el patron con DNI: ";

    public ResponseEntity<String> create(Patron patron) {
        String dni = patron.getDni();
        Optional<Patron> busqueda = patronRepository.findById(dni);
        try {
            if (busqueda.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un patron con DNI: " + dni);
            } else {
                patronRepository.save(patron);
                return ResponseEntity.status(HttpStatus.CREATED).body("Se ha creado el patron.");
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError);
        }
    }

    public ResponseEntity<String> create(Patron patron, String matricula) {
        String dni = patron.getDni();
        Optional<Patron> busqueda = patronRepository.findById(dni);
        Optional<Barco> busquedaBarco = barcoRepository.findById(matricula);
        try {
            if (busquedaBarco.isPresent()) {
                if (busqueda.isPresent()) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un patron con DNI: " + dni);
                } else {
                    patron.setBarco(busquedaBarco.get());
                    patronRepository.save(patron);
                    return ResponseEntity.status(HttpStatus.CREATED).body("Se ha creado el patron.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ningun barco con matriula: " + matricula);
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError + e.getLocalizedMessage());
        }
    }


    public ResponseEntity<?> findAll() {
        List<Patron> busqueda = patronRepository.findAll();
        try {
            if (!busqueda.isEmpty()) {
                return ResponseEntity.status(HttpStatus.FOUND).body(busqueda);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay ningun patron.");
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError + e.getMessage());
        }
    }

    public ResponseEntity<?> findById(String dni) {
        try {
            Optional<Patron> busqueda = patronRepository.findById(dni);
            if (busqueda.isPresent()) {
                return ResponseEntity.status(HttpStatus.FOUND).body(busqueda.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound + dni);
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError + e.getMessage());
        }
    }

    public ResponseEntity<String> updateById(Patron patron) {
        String dni = patron.getDni();
        Optional<Patron> update = patronRepository.findById(dni);
        try {
            if (update.isPresent()) {
                update.get().setNombre(patron.getNombre());
                update.get().setTelefono(patron.getTelefono());
                update.get().setBarco(patron.getBarco());
                patronRepository.save(update.get());
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Se cambiaron los datos del patron con DNI: " + dni);
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body(notFound + dni);
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError + e.getMessage());
        }
    }

    public ResponseEntity<String> updateWithBarco(String dni, String matricula) {
        Optional<Patron> update = patronRepository.findById(dni);
        Optional<Barco> barco = barcoRepository.findById(matricula);
        try {
            if (barco.isPresent()) {
                if (update.isPresent()) {
                    update.get().setBarco(barco.get());
                    patronRepository.save(update.get());
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Se cambiaron los datos del patron con DNI: " + dni);
                } else {
                    return ResponseEntity.status(HttpStatus.CREATED).body(notFound + dni);
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el Barco con matricula: " + matricula);
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError + e.getMessage());
        }
    }

    public ResponseEntity<String> deleteById(String dni) {
        try {
            Optional<Patron> barco = patronRepository.findById(dni);
            if (barco.isPresent()) {
                patronRepository.deleteById(dni);
                return ResponseEntity.status(HttpStatus.OK).body("Se ha eliminado el patron con la DNI: " + dni);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound + dni);
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError + e.getMessage());
        }
    }

}
