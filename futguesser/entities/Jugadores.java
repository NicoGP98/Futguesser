package com.futguesser.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.*;

/**
 *
 * @author 34661
 */
@Entity
@Table(name="JUGADORES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Jugadores {
    @Id
    @Column(name="ID")
    private int id;
    
    @Column(name="NOMBRE")
    private String nombre;
    
    @Column(name="STATS")
    private String stats;
    
    @Column(name="TRAYECTORIA")
    private String trayectoria;
    
    @Column(name="UCL")
    private int ucl;
    
    @Column(name="MUNDIAL")
    private int mundial;
    
    @Column(name="NACIONALIDAD")
    private String nacionalidad;
    
    @JsonBackReference
    @OneToMany(mappedBy="jugador")
    private List<EquiposAnios> equiposAnios;
    
}
