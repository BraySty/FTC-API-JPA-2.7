package com.ftc.jpa.entitys;

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
	@Column(name = "Salida")
	private Date salida;
	@Column(name = "Destino")
	private String destino;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Patron_DNI")
	private Patron patron;

}
