package com.nttdata.operation.controller;

import com.nttdata.operation.entity.Operation;
import com.nttdata.operation.model.Yanki;
import com.nttdata.operation.service.IOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/operation")
public class OperationController {

    @Autowired
    IOperationService service;

    @GetMapping
    public Flux<Operation> getOperations(){
        return service.getAll();
    }

    @PostMapping
    Mono<Operation> postOperation(@RequestBody Operation operation){
        return service.save(operation);
    }

    @GetMapping("/yanki/{phone}")
    public Mono<Yanki> getYanki(@PathVariable("phone") String phone){
        return service.getYanki(phone);
    }

    @PutMapping("/yanki")
    Mono<Yanki> updAccount(@RequestBody Yanki yanki){
        return service.updateYanki(yanki);
    }


    @PostMapping("/transferYanki")
    Mono<Operation> transferYanki(@RequestBody Operation operation){
        return service.transferYanki(operation);
    }
}
