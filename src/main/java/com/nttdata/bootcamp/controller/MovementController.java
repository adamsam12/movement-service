package com.nttdata.bootcamp.controller;

import com.nttdata.bootcamp.model.MovementCredit;
import com.nttdata.bootcamp.model.MovementDebit;
import com.nttdata.bootcamp.service.MovementCreditService;
import com.nttdata.bootcamp.service.MovementDebitService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movementBank")
public class MovementController {

    private final MovementDebitService movementDebitService;

    private static final String MOVEMENTDEBIT = "movementdebit";

    @GetMapping(value = "/debit/allMovementDebits")
    public Flux<MovementDebit> getAllMovementDebits(){
        System.out.println("Listar todos los movimientos de débito.");
        return movementDebitService.getAllMovementDebit();
    }

    @GetMapping(value = "/debit/{id}")
    public Mono<MovementDebit> getByIdMovementDebit(@PathVariable String id){
        System.out.println("Buscar movimiento de débito por ID.");
        return movementDebitService.getByIdMovementDebit(id);
    }

    @PostMapping(value = "/debit/create")
    @CircuitBreaker(name = MOVEMENTDEBIT, fallbackMethod = "movementdebit")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MovementDebit> createMovementDebit(@RequestBody MovementDebit movementDebit){
        System.out.println("Movimiento de débito creada con Éxito.");
        return movementDebitService.createMovementDebit(movementDebit);
    }

    @PutMapping(value = "/debit/update/{id}")
    @CircuitBreaker(name = MOVEMENTDEBIT, fallbackMethod = "movementdebit")
    public Mono<MovementDebit> updateMovementDebit(@PathVariable String id, @RequestBody MovementDebit movementDebit){
        System.out.println("Movimiento de débito actualizada con Éxito.");
        return movementDebitService.updateMovementDebit(id, movementDebit);
    }

    @DeleteMapping(value = "/debit/delete/{id}")
    public Mono<Void> deleteMovementDebit(@PathVariable String id){
        System.out.println("Movimiento de débito eliminada con Éxito.");
        return movementDebitService.deleteMovementDebit(id);
    }

    // Movement Credit

    private final MovementCreditService movementCreditService;

    private static final String MOVEMENTCREDIT = "movementcredit";

    @GetMapping(value = "/credit/allMovementCredits")
    public Flux<MovementCredit> getAllMovementCredits(){
        System.out.println("Lista todos los movimientos de créditos.");
        return movementCreditService.getAllMovementCredits();
    }

    @GetMapping(value = "/credit/{id}")
    public Mono<MovementCredit> getByIdMovementCredit(@PathVariable String id){
        System.out.println("Busca un movimiento de créditos por ID.");
        return movementCreditService.getMovementCreditById(id);
    }

    @PostMapping(value = "/credit/create")
    @CircuitBreaker(name = MOVEMENTCREDIT, fallbackMethod = "movementcredit")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MovementCredit> createMovementCredit(@RequestBody MovementCredit movementCredit){
        System.out.println("Movimiento de créditos creada con Éxito.");
        return movementCreditService.createMovementCredit(movementCredit);
    }

    @PutMapping(value = "/credit/update/{id}")
    @CircuitBreaker(name = MOVEMENTCREDIT, fallbackMethod = "movementcredit")
    public Mono<MovementCredit> updateMovementCredit(@PathVariable String id, @RequestBody MovementCredit movementCredit){
        System.out.println("Movimiento de créditos actualizada con Éxito.");
        return movementCreditService.updateMovementCredit(id, movementCredit);
    }

    @DeleteMapping(value = "/credit/delete/{id}")
    public Mono<Void> deleteMovementCredit(@PathVariable String id){
        System.out.println("Movimiento de créditos eliminada con Éxito.");
        return movementCreditService.deleteMovementCredit(id);
    }


}