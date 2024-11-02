package com.glsid.practicalcqrsandeventsourcingwithaxonframework.query.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.commands.enums.OperationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private OperationStatus status;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private  Account account;
}
