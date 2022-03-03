package com.nttdata.operation.repository;

import com.nttdata.operation.entity.Operation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOperationRepository extends ReactiveMongoRepository<Operation,String> {
}
