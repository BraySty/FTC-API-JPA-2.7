package com.ftc.jpa.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@Column(name = "Nombre")
	private String nombre;
	@Column(name = "Amarre")
	private int amarre;
	@Column(name = "Cuota")
	private Double cuota;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Patron_DNI")
	private Patron patron;

	public Barco (String matricula, String nombre, int amarre, double cuota) {
		this.matricula = matricula;
		this.nombre = nombre;
		this.amarre = amarre;
		this.cuota = cuota;
		this.patron = null;
	}

}
