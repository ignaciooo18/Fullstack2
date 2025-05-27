package com.duoc.Edutech.services;

import com.duoc.Edutech.model.Inscripcion;
import com.duoc.Edutech.repository.InscripcionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class InscripcionServices {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    public Inscripcion save(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    public Inscripcion deleteById(Inscripcion inscripcion) {
        Inscripcion inscripcionExistente = inscripcionRepository.findById(inscripcion.getIdInscripcion())
                .orElse(null);
        if (inscripcionExistente != null) {
            inscripcionRepository.deleteById(inscripcion.getIdInscripcion());
        }
        return inscripcionExistente;
    }
}