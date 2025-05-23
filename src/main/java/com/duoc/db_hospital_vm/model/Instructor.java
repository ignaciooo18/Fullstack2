package com.duoc.db_hospital_vm.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Instructor")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Instructor {
@Id
private String mencion;

@Column (nullable = false)
private Date fechaIngreso;
}
