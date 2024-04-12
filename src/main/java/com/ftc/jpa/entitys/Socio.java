package com.ftc.jpa.entitys;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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
@Table(name = "socio", catalog = "club_nautico")
public class Socio implements java.io.Serializable {
    
    @Id
	@Column(name = "DNI", unique = true, nullable = false, length = 9)
    private String dni;
    @Column(name = "Nombre")
	private String nombre;
    @Column(name = "Direccion")
	private String direccion;
    @Column(name = "Telefono")
	private Integer telefono;
    @Column(name = "Email")
	private String email;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "socio")
	@Builder.Default
    @JsonManagedReference
    private Set<Barco> barcos = new HashSet<>(0);

    public void addBarcos(Barco barco) {
        this.barcos.add(barco);
    }

}
