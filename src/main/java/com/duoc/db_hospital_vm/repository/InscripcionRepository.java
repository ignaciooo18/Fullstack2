package com.duoc.db_hospital_vm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duoc.db_hospital_vm.model.Inscripcion;
@Repository
public interface InscripcionRepository extends JpaRepository <Inscripcion,Integer> {

}
