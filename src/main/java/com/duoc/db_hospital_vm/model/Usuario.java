package com.duoc.db_hospital_vm.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
@Table (name = "Usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer idUsuario;

@Column(unique = true,nullable = false,length = 8)
private Integer rutUsuario;

@Column (nullable = false)
private String nombre;

@Column (unique = true, nullable = false)
private String correo;
@Column (nullable = false)
private String Contraseña;
@Column (nullable = false)
private String estado;

@Column (nullable = false)
private Date fechaCreacion;


}
