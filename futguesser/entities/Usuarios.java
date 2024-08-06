package com.futguesser.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
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
@Table(name="usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuarios /*implements UserDetails*/ {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idUsuario")
    private Long idUsuario;
    
    @Column (name = "nombreUsuario")
    private String nombreUsuario;
    
    @Column (name = "email")
    private String email;
    
    @Column (name = "password")
    private String password;    
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="usuario_roles", joinColumns = @JoinColumn( name = "usuario_id", referencedColumnName = "idUsuario")
    , inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "idRol"))
    private List<Roles>  roles = new ArrayList();
    
    @JsonBackReference
    @OneToMany(mappedBy="usuario")
    private List<Estadisticas> estadisticasUsuario;

}

