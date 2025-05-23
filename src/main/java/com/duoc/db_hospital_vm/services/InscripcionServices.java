package com.duoc.db_hospital_vm.services;

import com.duoc.db_hospital_vm.model.Inscripcion;
import com.duoc.db_hospital_vm.repository.InscripcionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class InscripcionServices {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    public Inscripcion save(Inscripcion inscripcion) {
        return  inscripcionRepository.save(inscripcion);
    }
}
