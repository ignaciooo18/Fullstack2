package com.duoc.db_hospital_vm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.db_hospital_vm.model.Pago;
import com.duoc.db_hospital_vm.repository.PagoRepository;

import jakarta.transaction.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PagoServices {
    
    @Autowired
    private PagoRepository pagoRepository;
    
    public Pago save(Pago pago) {
        return pagoRepository.save(pago);
    }
    
    public Optional<Pago> findById(Integer idPago) {
        return pagoRepository.findById(idPago);
    }
}