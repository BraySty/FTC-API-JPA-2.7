package com.ftc.jpa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ftc.jpa.entitys.Registro;
import com.ftc.jpa.repository.RegistroRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistroService {

    @Autowired
    private RegistroRepository registroRepository;

    private String internalError = "Se ha producido un error.";
    private String notFound = "No se encontro el registro con ID: ";

    public ResponseEntity<String> create(Registro registro) {
        int id = registro.getIdRegistro();
        Optional<Registro> busqueda = registroRepository.findById(id);
        try {
            if (busqueda.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un registro con la ID: " + id);
            } else {
                registroRepository.save(registro);
                System.out.println(registroRepository.findTopByOrderByIdRegistroDesc());
                return ResponseEntity.status(HttpStatus.CREATED).body("Se ha creado el registro con la ID:" + registroRepository.findTopByOrderByIdRegistroDesc());
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError + "\n" + e.getMessage());
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
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError);
        }
    }

    public ResponseEntity<String> updateById(Registro registro) {
        int id = registro.getIdRegistro();
        Optional<Registro> update = registroRepository.findById(id);
        try {
            if (update.isPresent()) {
                update.get().setSalida(registro.getSalida());
                update.get().setDestino(registro.getDestino());
                update.get().setPatron(registro.getPatron());
                registroRepository.save(update.get());
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Se cambiaron los datos del registro con ID: " + id);
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body(notFound + id);
            }
        } catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError);
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
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalError);
        }
    }

}
