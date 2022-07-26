package com.nttdata.bootcamp.repository;

import com.nttdata.bootcamp.model.MovementDebit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MovementDebitRepository extends ReactiveMongoRepository<MovementDebit, String> {

}
