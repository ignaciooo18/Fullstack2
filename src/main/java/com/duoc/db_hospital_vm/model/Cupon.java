package com.duoc.db_hospital_vm.model;


import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table (name="Cupon")
@NoArgsConstructor
@AllArgsConstructor

public class Cupon {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer idcupon;

@Column(unique=true,nullable=false)
private String codigo;

@Column(nullable = false)
private char tipoDescuento;

@Column(nullable = false)
private Integer valor;

@Column (nullable = false)
private Date fecha_vencimiento;

@Column(nullable = false)
private String estado;
}
