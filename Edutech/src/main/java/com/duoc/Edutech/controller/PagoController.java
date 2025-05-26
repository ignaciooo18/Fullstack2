package com.duoc.Edutech.controller;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.duoc.Edutech.model.Pago;
import com.duoc.Edutech.repository.InscripcionRepository;
import com.duoc.Edutech.services.PagoServices;

@RestController
@RequestMapping("/api/pagoEdutech")
public class PagoController {

    @Autowired
    private PagoServices pagoServices;

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @GetMapping("/consultarpago/{idpago}")
    public ResponseEntity<?> consultarpago(@PathVariable Integer idpago) {
        return pagoServices.findById(idpago)
                .map(pago -> {
                    System.out.println("Pago encontrado: " + pago);
                    return ResponseEntity.ok(pago);
                })
                .orElseGet(() -> {
                    System.out.println("No se encontró pago con ID: " + idpago);
                    return ResponseEntity.notFound().build();
                });
    }
    @GetMapping("/eliminarpago/{idpago}")
    public ResponseEntity<?> eliminarpago(@PathVariable Integer idpago) {
        return pagoServices.findById(idpago)
                .map(pago -> {
                    pagoServices.deleteById(idpago);
                    return ResponseEntity.ok("Pago eliminado con éxito");
                })
                .orElseGet(() -> {
                    return ResponseEntity.notFound().build();
                });
    }
    @PostMapping("/pagar/{idInscripcion}")
    public ResponseEntity<?> pagarcurso(@PathVariable Integer idInscripcion, @RequestBody Pago nuevoPago) {
        return inscripcionRepository.findById(idInscripcion)
                .map(inscripcion -> {
                    nuevoPago.setInscripcion(inscripcion);
                    nuevoPago.setFecha_pago(LocalDate.now());
                    if (nuevoPago.getMonto() == null) {
                        int montoaleatorio = (int) (Math.random() * (10000 - 5000 + 1) + 5000);
                        nuevoPago.setMonto(montoaleatorio);
                    }

                    if (nuevoPago.getMontoPagos() == null) {
                        return ResponseEntity.badRequest().body("El monto a pagar es necesario");
                    }

                    if (nuevoPago.getMetodopago() == null) {
                        return ResponseEntity.badRequest().body("El método de pago es necesario");
                    }

                    if (nuevoPago.getMontoPagos() >= nuevoPago.getMonto()) {
                        nuevoPago.setEstado("aprobado");
                        nuevoPago.getInscripcion().setEstadoInscripcion("pagado");
                    } else {
                        nuevoPago.setEstado("rechazado");
                        nuevoPago.getInscripcion().setEstadoInscripcion("no pagado");
                    }

                    Pago pagoGuardado = pagoServices.save(nuevoPago);
                    return ResponseEntity.ok(pagoGuardado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}