package com.nttdata.bootcamp.repository;

import com.nttdata.bootcamp.model.MovementCredit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MovementCreditRepository extends ReactiveMongoRepository<MovementCredit, String> {

}
