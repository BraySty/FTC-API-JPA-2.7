package com.ftc.jpa.classes;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registro", catalog = "club_nautico")
public class Registro implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_Registro", unique = true, nullable = false)
	private int idRegistro;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Barco_Matricula", nullable = false)
	private Barco barco;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Patron_DNI", nullable = false)
	private Patron patron;
	@Temporal(TemporalType.DATE)
	@Column(name = "Entrada", length = 10)
	private Date entrada;
	@Temporal(TemporalType.DATE)
	@Column(name = "Salida", length = 10)
	private Date salida;
	@Column(name = "Destino")
	private String destino;

}
