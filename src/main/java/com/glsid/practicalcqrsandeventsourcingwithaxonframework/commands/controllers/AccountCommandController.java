package com.glsid.practicalcqrsandeventsourcingwithaxonframework.commands.controllers;

import com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.commands.CreateAccountCommand;
import com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.commands.CreditAccountCommand;
import com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.commands.DebitAccountCommand;
import com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.dtos.CreateAccountRequestDTO;
import com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.dtos.CreditAccountRequestDTO;
import com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.dtos.DebitAccountRequestDTO;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;


@RestController
@RequestMapping(value = "/commands/accounts")
@AllArgsConstructor
public class AccountCommandController {

    // 1/ commandGateway is used to send commands to the command bus
    private CommandGateway commandGateway;

    // This is used to read events from the event store
    private EventStore eventStore;

    @PostMapping(path = "/create")
    public CompletableFuture<String> createAccount(@RequestBody CreateAccountRequestDTO request) {
        // when we create an account, we need to send a command to the command bus
        // so that the command handler can handle it
        return commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID().toString(),
                request.getInitialBalance(),
                request.getCurrency()
        ));
    }

    @PutMapping("/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountRequestDTO credit) {
        return commandGateway.send(
                new CreditAccountCommand(
                        credit.getId(),
                        credit.getAmount(),
                        credit.getCurrency()
                )
        );
    }

    @PutMapping("/debit")
    public CompletableFuture<String> debitAccount(@RequestBody DebitAccountRequestDTO debit) {
        return commandGateway.send(
                new DebitAccountCommand(
                        debit.getId(),
                        debit.getAmount(),
                        debit.getCurrency()
                )
        );
    }

    // read
    @GetMapping("/eventStore/{accountId}")
    public Stream eventStore(@PathVariable String accountId) {
        return eventStore.readEvents(accountId).asStream();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}