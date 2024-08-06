package com.futguesser.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name= "EQUIPOS_ANIOS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EquiposAnios {
    
    @Id
    @Column(name="ID")
    private int id;
    
    @ManyToOne
    @JoinColumn(name="IDJUGADOR", referencedColumnName="ID")
    private Jugadores jugador;

    @ManyToOne
    @JoinColumn(name="IDEQUIPO", referencedColumnName="ID")
    private Equipos equipo;
    
    @Column(name="ANIO")
    private String anio;
}
