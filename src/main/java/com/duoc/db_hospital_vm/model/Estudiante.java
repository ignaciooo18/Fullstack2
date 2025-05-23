package com.duoc.db_hospital_vm.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@DiscriminatorValue("ESTUDIANTE")
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante extends Usuario{
@Column (nullable = false)
private Integer promedio;
@Column (nullable = false)
Date fecha_registro;
}
