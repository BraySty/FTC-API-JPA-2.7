package com.ftc.jpa.entitys;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patron", catalog = "club_nautico")
public class Patron implements java.io.Serializable {

	@Id
	@Column(name = "DNI", unique = true, nullable = false, length = 9)
	private String dni;
	@Column(name = "Nombre")
	private String nombre;
	@Column(name = "Telefono")
	private Integer telefono;
	@Column(name = "Socio", length = 7)
	private String socio;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patron")
	@Builder.Default
	private Set<Registro> registros = new HashSet<>(0);
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patron")
	@Builder.Default
	private Set<Barco> barcos = new HashSet<>(0);

}
