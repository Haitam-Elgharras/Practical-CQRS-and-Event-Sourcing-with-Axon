package com.glsid.practicalcqrsandeventsourcingwithaxonframework.query.entities;

import com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.commands.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    private String id;
    private double balance;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @OneToMany(mappedBy = "account")
    private Collection<Operation> operations;
}
