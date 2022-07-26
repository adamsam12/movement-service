package com.nttdata.bootcamp.service.impl;

import com.nttdata.bootcamp.model.MovementDebit;
import com.nttdata.bootcamp.repository.MovementDebitRepository;
import com.nttdata.bootcamp.service.MovementDebitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovementDebitServiceImpl implements MovementDebitService {

    @Autowired
    MovementDebitRepository movementDebitRepository;

    @Override
    public Flux<MovementDebit> getAllMovementDebit() {
        return movementDebitRepository.findAll();
    }

    @Override
    public Mono<MovementDebit> getByIdMovementDebit(String id) {
        return movementDebitRepository.findById(id);
    }

    @Override
    public Mono<MovementDebit> createMovementDebit(MovementDebit movementDebit) {
        return movementDebitRepository.save(movementDebit);
    }

    @Override
    public Mono<MovementDebit> updateMovementDebit(String id, MovementDebit movementDebit) {
        return movementDebitRepository.findById(id).flatMap(movementDebit1 -> {
            movementDebit1.setAmount(movementDebit.getAmount());
            movementDebit1.setDateLimit(movementDebit.getDateLimit());
            movementDebit1.setCommission(movementDebit.getCommission());
            movementDebit1.setDescription(movementDebit.getDescription());
            return movementDebitRepository.save(movementDebit1);
        }).switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Void> deleteMovementDebit(String id) {
        return movementDebitRepository.findById(id).flatMap(movementDebit -> movementDebitRepository.deleteById(movementDebit.getId()));
    }
}
