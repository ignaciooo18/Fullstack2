package com.duoc.db_hospital_vm.model;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table (name ="Pago")
public class Pago {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer idpago;

@Column(nullable = false)
private Integer monto;
@Column(nullable = false)
private LocalDate fecha_pago;
@Column(name ="metodo_pago",nullable = false)
private String metodopago;
@Column(nullable = false)
private Integer montoPagos;
@Column(nullable = false)
private String estado;
@ManyToOne
@JoinColumn (name = "idInscripcion")
private Inscripcion inscripcion;
}
