package com.futguesser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author 34661
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EstadisticasDTO {
    
   public boolean acertado;
   
   public int numIntentos;
   
   private int aciertos;
   
   private int fallos;
   
   private int[] aciertosPorIntento;
}
