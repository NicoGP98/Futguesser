package com.futguesser.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author 34661
 */

@Entity
@Table(name="EQUIPOS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Equipos {
    
   @Id
   @Column(name= "ID")
    private int id;
    
   @Column(name="NOMBRE")
    private String nombre;
    
   @Column(name="ESCUDO")
    private String escudo;
   
   @JsonBackReference
   @OneToMany(mappedBy="equipo")
    private List<EquiposAnios> equiposAnios;
   
}
