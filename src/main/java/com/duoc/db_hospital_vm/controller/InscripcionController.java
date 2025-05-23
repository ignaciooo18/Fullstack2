package com.duoc.db_hospital_vm.controller;

import com.duoc.db_hospital_vm.model.Inscripcion;
import com.duoc.db_hospital_vm.services.InscripcionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/crearInscripcion")
public class InscripcionController {
    @Autowired
    private InscripcionServices inscripcionServices;

    @PostMapping
    public ResponseEntity<Inscripcion> save(@RequestBody Inscripcion inscripcion) {
        inscripcion.setFechaInscripcion(LocalDate.now());
        return ResponseEntity.ok(inscripcionServices.save(inscripcion));
    }
}