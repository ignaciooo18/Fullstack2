package com.duoc.Edutech.controller;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Map;

import com.duoc.Edutech.model.Cupon;
import com.duoc.Edutech.services.CuponServices;
import org.apache.coyote.Response;
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
    @Autowired
    private CuponServices cuponServices;

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
    @PostMapping("/pagar/{idInscripcion}/{idcupon}")
    public ResponseEntity<?> pagarcurso(@PathVariable Integer idInscripcion,
                                        @PathVariable Integer idcupon,
                                        @RequestBody Pago nuevoPago) {
        return inscripcionRepository.findById(idInscripcion)
                .map(inscripcion -> {
                    Optional<Cupon> cupon = cuponServices.findById(idcupon);
                    if (cupon.isEmpty()) {
                        return ResponseEntity.badRequest().body("El cupon no existe");
                    }

                    Cupon cup = cupon.get();

                    if (cup.getEstado().equals("usado")||cup.getEstado().equals("canjeado")){
                        return ResponseEntity.badRequest().body("El cupon esta vencido/canjeado");
                    }

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
                    

                    if (nuevoPago.getMontoPagos() >= nuevoPago.getMonto() - (nuevoPago.getMonto()*cup.getDescuento()/100)
                    ) {
                        nuevoPago.setEstado("aprobado");
                        nuevoPago.getInscripcion().setEstadoInscripcion("pagado");
                    } else {
                        nuevoPago.setEstado("rechazado");
                        nuevoPago.getInscripcion().setEstadoInscripcion("no pagado");
                    }


                    Pago pagoGuardado = pagoServices.save(nuevoPago);
                    return ResponseEntity.ok(Map.of(
                        "pago", pagoGuardado,
                        "descuento Aplicado", cup.getDescuento(),
                        "monto a pagar ", nuevoPago.getMonto(),
                        "monto final a pagar", nuevoPago.getMonto() - (nuevoPago.getMonto()*cup.getDescuento()/100)
                    ));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}