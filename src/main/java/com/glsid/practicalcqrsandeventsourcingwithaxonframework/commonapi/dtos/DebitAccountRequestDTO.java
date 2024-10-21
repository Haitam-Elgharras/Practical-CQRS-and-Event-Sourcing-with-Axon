package com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DebitAccountRequestDTO {
    private String id;
    private Double amount;
    private String currency;
}