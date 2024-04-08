package com.ftc.jpa.classes;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "barco", catalog = "club_nautico")
public class Barco implements java.io.Serializable {

	@Id
	@Column(name = "Matricula", unique = true, nullable = false)
	private String matricula;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Patron_DNI", nullable = false)
	private Patron patron;
	@Column(name = "Nombre")
	private String nombre;
	@Column(name = "Amarre")
	private String amarre;
	@Column(name = "Cuota", precision = 22, scale = 0)
	private Double cuota;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "barco")
	private Set<Registro> registros = new HashSet<>(0);

}
