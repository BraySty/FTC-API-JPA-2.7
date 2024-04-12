package com.ftc.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.ftc.jpa.entitys.Patron;
import com.ftc.jpa.repository.BarcoRepository;
import com.ftc.jpa.repository.PatronRepository;
import com.ftc.jpa.repository.RegistroRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE) // Indicamos que vamos a trabajar con la BBDD que tenemos configurada en el proyecto.
@TestMethodOrder(OrderAnnotation.class) // Indicamos si vamos a suministrar un orden para los test, de no hacerlo se ejecutara en orden alfabetico.
class JpaApplicationTests {

	@Autowired
	private BarcoRepository barcoRepo;
	@Autowired
	private PatronRepository patronRepo;
	@Autowired
	private RegistroRepository registroRepo;

	@Test
	@Order(1)
	@Rollback(false)
	void testInsertarPatron() {
		Patron patron = new Patron("12345678A", "Carlos,",922822722,null,null);
		Patron patronGuardado = patronRepo.save(patron);
		assertNotNull(patronGuardado);
	}

	@Test
	@Order(2)
	@Rollback(false)
	void testBuscarPorIDPatron() {
		String dni = "12345678A";
		Patron buscar = patronRepo.findById(dni).get();
		assertNotNull(buscar);
		assertEquals(dni, buscar.getDni());
	}

	@Test
	@Order(3)
	@Rollback(false)
	void testActualizarPorIDPatron() {
		String dni = "12345678A";
		Patron actualizar = patronRepo.findById(dni).get();
		int telefono = actualizar.getTelefono();
		actualizar.setTelefono(633400500);
		patronRepo.save(actualizar);
		actualizar = patronRepo.findById(dni).get();
		assertNotEquals(telefono, actualizar.getTelefono());
	}

	@Test
	@Order(4)
	@Rollback(false)
	void testEliminarPorIDPatron() {
		String dni = "12345678A";
		Patron eliminar = null;
		patronRepo.deleteById(dni);
		eliminar = patronRepo.findById(dni).get();
		assertNull(eliminar);
	}

}
