package com.nttdata.operation.service;

import com.nttdata.operation.entity.Operation;
import com.nttdata.operation.model.Yanki;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IOperationService {

    Flux<Operation> getAll();

    Mono<Operation> save(Operation operation);

    Mono<Yanki> getYanki(String phone);

    Mono<Yanki> updateYanki(Yanki yanki);

    Mono<Operation> transferYanki(Operation operation);
}
