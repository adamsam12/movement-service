package com.nttdata.bootcamp.service.impl;

import com.nttdata.bootcamp.model.MovementCredit;
import com.nttdata.bootcamp.repository.MovementCreditRepository;
import com.nttdata.bootcamp.service.MovementCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovementCreditServiceImpl implements MovementCreditService {

    @Autowired
    MovementCreditRepository movementCreditRepository;

    @Override
    public Flux<MovementCredit> getAllMovementCredits() {
        return movementCreditRepository.findAll();
    }

    @Override
    public Mono<MovementCredit> getMovementCreditById(String id) {
        return movementCreditRepository.findById(id);
    }

    @Override
    public Mono<MovementCredit> createMovementCredit(MovementCredit movementCredit) {
        return movementCreditRepository.save(movementCredit);
    }

    @Override
    public Mono<MovementCredit> updateMovementCredit(String id, MovementCredit movementCredit) {
        return movementCreditRepository.findById(id).flatMap(movementCredit1 -> {
            movementCredit1.setAmount(movementCredit.getAmount());
            movementCredit1.setDateLimit(movementCredit.getDateLimit());
            movementCredit1.setCommission(movementCredit.getCommission());
            movementCredit1.setDescription(movementCredit.getDescription());
            return movementCreditRepository.save(movementCredit1);
        }).switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Void> deleteMovementCredit(String id) {
        return movementCreditRepository.findById(id).flatMap(movementCredit -> movementCreditRepository.deleteById(movementCredit.getId()));
    }
}
