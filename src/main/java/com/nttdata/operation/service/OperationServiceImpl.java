package com.nttdata.operation.service;

import com.nttdata.operation.entity.Operation;
import com.nttdata.operation.model.Yanki;
import com.nttdata.operation.repository.IOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OperationServiceImpl implements IOperationService {

    @Autowired
    IOperationRepository repository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public Flux<Operation> getAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Operation> save(Operation operation) {
        return repository.save(operation);
    }

    @Override
    public Mono<Yanki> getYanki(String phone) {
        Mono<Yanki> yankiMono = webClientBuilder.build()
                .get()
                .uri("http://localhost:8009/yanki/phone/{phone}", phone)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Yanki.class);
        return yankiMono;
    }

    @Override
    public Mono<Yanki> updateYanki(Yanki yanki) {
        Mono<Yanki> yankiMono = webClientBuilder.build()
                .put()
                .uri("http://localhost:8009/yanki")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(yanki))
                .retrieve()
                .bodyToMono(Yanki.class);
        return yankiMono;
    }

    @Override
    public Mono<Operation> transferYanki(Operation operation) {
         getYanki(operation.getPhoneSend()).flatMap(a -> {
             a.setBalance(a.getBalance() - operation.getAmount());

             return updateYanki(a);
         }).subscribe();
        getYanki(operation.getPhoneReceive()).flatMap(b -> {
            b.setBalance(b.getBalance() + operation.getAmount());

            return updateYanki(b);
        }).subscribe();

        return repository.save(operation);
    }


}
