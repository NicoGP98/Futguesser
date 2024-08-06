package com.futguesser.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name="estadisticas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Estadisticas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "idEstadisticas")
    private Long idEstadisticas;
    
    @Column (name = "acertado")
    private boolean acertado;
    
    @Column (name = "numIntentos")
    private int numIntentos;
    
    @ManyToOne
    @JoinColumn(name="usuario", referencedColumnName="idUsuario")
    private Usuarios usuario;
}
