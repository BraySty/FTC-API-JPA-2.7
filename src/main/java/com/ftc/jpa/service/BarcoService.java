package com.ftc.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ftc.jpa.entitys.Barco;
import com.ftc.jpa.entitys.Socio;
import com.ftc.jpa.repository.BarcoRepository;
import com.ftc.jpa.repository.SocioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BarcoService {

    @Autowired
    private BarcoRepository barcoRepository;
    @Autowired
    private SocioRepository socioRepository;

    private String internalError = "Se ha producido un error.\n";
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
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError + e.getLocalizedMessage());
        }
    }

    public ResponseEntity<String> create(Barco barco, String dni) {
        String matricula = barco.getMatricula();
        Optional<Barco> busqueda = barcoRepository.findById(matricula);
        Optional<Socio> busquedaSocio = socioRepository.findById(dni);
        try {
            if (busquedaSocio.isPresent()) {
                if (busqueda.isPresent()) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un barco con matriula: " + matricula);
                } else {
                    barco.setSocio(busquedaSocio.get());
                    barcoRepository.save(barco);
                    return ResponseEntity.status(HttpStatus.CREATED).body("Se ha creado el barco.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ningun socio con DNI: " + dni);
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError + e.getLocalizedMessage());
        }
    }

    public ResponseEntity<?> findAll() {
        List<Barco> busqueda = barcoRepository.findAll();
        try {
            if (!busqueda.isEmpty()) {
                return ResponseEntity.status(HttpStatus.FOUND).body(busqueda);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay ningun barco.");
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError + e.getLocalizedMessage());
        }
    }

    public ResponseEntity<?> findById(String matricula) {
        try {
            Optional<Barco> busqueda = barcoRepository.findById(matricula);
            if (busqueda.isPresent()) {
                return ResponseEntity.status(HttpStatus.FOUND).body(busqueda.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound + matricula);
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError + e.getLocalizedMessage());
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
                update.get().setPatrones(barco.getPatrones());
                update.get().setRegistros(barco.getRegistros());
                barcoRepository.save(update.get());
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Se cambiaron los datos del barco con matricula: " + matricula);
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body(notFound + matricula);
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError + e.getLocalizedMessage());
        }
    }

    public ResponseEntity<?> update(String matricula, String dni) {
        Optional<Barco> update = barcoRepository.findById(matricula);
        Optional<Socio> busqueda = socioRepository.findById(dni);
        try {
            if (busqueda.isPresent()) {
                if (update.isPresent()) {
                    update.get().setSocio(busqueda.get());
                    barcoRepository.save(update.get());
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Se cambiaron los datos del barco con matricula: " + matricula);
                } else {
                    return ResponseEntity.status(HttpStatus.CREATED).body(notFound + matricula);
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el socio con DNI: " + dni);
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError + e.getLocalizedMessage());
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
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError + e.getMessage());
        }
    }

}
