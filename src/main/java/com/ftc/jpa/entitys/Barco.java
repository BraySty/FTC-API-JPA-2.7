package com.ftc.jpa.entitys;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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
	@Column(name = "Nombre")
	private String nombre;
	@Column(name = "Amarre")
	private String amarre;
	@Column(name = "Cuota")
	private Double cuota;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Socio_DNI")
	private Socio socio;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "barco", cascade = CascadeType.ALL)
	@Builder.Default
	@JsonManagedReference
	private Set<Patron> patrones = new HashSet<>(0);
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "barco", cascade = CascadeType.ALL)
	@Builder.Default
	@JsonManagedReference
	private Set<Registro> registros = new HashSet<>(0);

	public void addPatrones(Patron patrone) {
        this.patrones.add(patrone);
    }

	public void addRegistros(Registro registro) {
        this.registros.add(registro);
    }	

}
