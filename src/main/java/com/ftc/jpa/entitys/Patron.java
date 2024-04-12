package com.ftc.jpa.entitys;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "patron", catalog = "club_nautico")
public class Patron implements java.io.Serializable {

	@Id
	@Column(name = "Patron_DNI", unique = true, nullable = false, length = 9)
	private String dni;
	@Column(name = "Nombre")
	private String nombre;
	@Column(name = "Telefono")
	private Integer telefono;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Barco_Matricula")
	@JsonBackReference
	private Barco barco;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "patron", cascade = CascadeType.ALL)
	@Builder.Default
	@JsonManagedReference
	private Set<Registro> registros = new HashSet<>(0);

	public void addRegistros(Registro registro) {
        this.registros.add(registro);
    }

}
