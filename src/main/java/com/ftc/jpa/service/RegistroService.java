package com.ftc.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ftc.jpa.entitys.Barco;
import com.ftc.jpa.entitys.Patron;
import com.ftc.jpa.entitys.Registro;
import com.ftc.jpa.repository.BarcoRepository;
import com.ftc.jpa.repository.PatronRepository;
import com.ftc.jpa.repository.RegistroRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistroService {

    @Autowired
    private RegistroRepository registroRepository;
    @Autowired
    private PatronRepository patronRepository;
    @Autowired
    private BarcoRepository barcoRepository;
    
    private String internalError = "Se ha producido un error.\n";
    private String notFound = "No se encontro el registro con ID: ";

    public ResponseEntity<String> create(Registro registro) {
        int id = registro.getIdRegistro();
        Optional<Registro> busqueda = registroRepository.findById(id);
        try {
            if (busqueda.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un registro con la ID: " + id);
            } else {
                registroRepository.save(registro);
                return ResponseEntity.status(HttpStatus.CREATED).body("Se ha creado el registro con la ID: " + registroRepository.findFirstByOrderByIdRegistroDesc().get(0).getIdRegistro());
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError + e.getLocalizedMessage());
        }
    }

    public ResponseEntity<?> findAll() {
        List<Registro> busqueda = registroRepository.findAll();
        try {
            if (!busqueda.isEmpty()) {
                return ResponseEntity.status(HttpStatus.FOUND).body(busqueda);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay ningun registro.");
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError + e.getLocalizedMessage());
        }
    }

    public ResponseEntity<?> findById(int id) {
        try {
            Optional<Registro> registro = registroRepository.findById(id);
            if (registro.isPresent()) {
                return ResponseEntity.status(HttpStatus.FOUND).body(registro.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound + id);
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError + e.getLocalizedMessage());
        }
    }

    public ResponseEntity<String> updateById(Registro registro) {
        int id = registro.getIdRegistro();
        Optional<Registro> update = registroRepository.findById(id);
        try {
            if (update.isPresent()) {
                update.get().setSalida(registro.getSalida());
                update.get().setDestino(registro.getDestino());
                update.get().setBarco(registro.getBarco());
                registroRepository.save(update.get());
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Se cambiaron los datos del registro con ID: " + id);
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body(notFound + id);
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError + e.getLocalizedMessage());
        }
    }

    public ResponseEntity<String> updateRegistroWithBarco(int id, String matricula) {
        Optional<Registro> update = registroRepository.findById(id);
        Optional<Barco> barco = barcoRepository.findById(matricula);
        try {
            if (barco.isPresent()) {
                if (update.isPresent()) {
                    update.get().setBarco(barco.get());
                    registroRepository.save(update.get());
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Se cambiaron los datos del registro con ID: " + id);
                } else {
                    return ResponseEntity.status(HttpStatus.CREATED).body(notFound + id);
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el Barco con matricula: " + matricula);
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError + e.getLocalizedMessage());
        }
    }

    public ResponseEntity<String> updateRegistroWithPatron(int id, String dni) {
        Optional<Registro> update = registroRepository.findById(id);
        Optional<Patron> patron = patronRepository.findById(dni);
        try {
            if (patron.isPresent()) {
                if (update.isPresent()) {
                    update.get().setPatron(patron.get());
                    registroRepository.save(update.get());
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Se cambiaron los datos del registro con ID: " + id);
                } else {
                    return ResponseEntity.status(HttpStatus.CREATED).body(notFound + id);
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el Patron con DNI: " + dni);
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError + e.getLocalizedMessage());
        }
    }

    public ResponseEntity<String> deleteById(int id) {
        try {
            Optional<Registro> registro = registroRepository.findById(id);
            if (registro.isPresent()) {
                registroRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body("Se ha eliminado el barco con la ID: " + id);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound + id);
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError + e.getLocalizedMessage());
        }
    }

}

















































































